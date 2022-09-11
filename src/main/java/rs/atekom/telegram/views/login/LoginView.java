package rs.atekom.telegram.views.login;

import java.math.BigDecimal;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import rs.atekom.telegram.data.entity.Token;
import rs.atekom.telegram.security.AuthenticatedUser;
import rs.atekom.telegram.security.UserDetailsServiceImpl;
import rs.atekom.telegram.views.MainLayout;
import rs.atekom.telegram.views.helloworld.HelloWorldView;

@JavaScript("./js/script.js")
@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = 1L;
	private final AuthenticatedUser authenticatedUser;
    private Element element;
    private final UserDetailsServiceImpl userService;

    public LoginView(AuthenticatedUser authenticatedUser, UserDetailsServiceImpl userService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        
        //for test server
        //String telegram1 = "<script async src=\"https://telegram.org/js/telegram-widget.js?19\" data-telegram-login=\"test_cctime_bot\" data-size=\"large\" "
              //+ "data-onauth=\"onTelegramAuth(user)\"></script>";
        
        //for local development
        String telegram1 = "<script async src=\"https://telegram.org/js/telegram-widget.js?19\" data-telegram-login=\"dev_cctime_bot\" data-size=\"large\" "
        		+ "data-onauth=\"onTelegramAuth(user)\"></script>";
        
        Image image = new Image("images/neomatic.png", "Neomatic");
        //image.setWidth("200px");
        add(image);
        H1 title = new H1("Neomatic TimeSheet");
        Html html = new Html(telegram1);
        add(title, html);
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        setSpacing(true);

        element = getElement();
        element.executeJs("setroot($0)", element);
    }
    
    @ClientCallable
    public void enterApp(String id, String first_name, String last_name, String username, String photo_url, String auth_date, String hash) {
    	BigDecimal bdID = new BigDecimal(id);
    	BigDecimal bdAuthDate = new BigDecimal(auth_date);
    	if (id != null) {
    		try {
    			Token token = new Token(bdID.longValue(), first_name == null ? "" : first_name, 
    					last_name == null ? "" : last_name, username == null ? "" : username, 
    							photo_url ==null ? "" : photo_url, bdAuthDate.toPlainString(), hash);
    			token.setBot(MainLayout.BOTNAME);
    			userService.authenticate(token);
    			}catch (Exception e) {
    				e.printStackTrace();
    				}
                UI.getCurrent().navigate(HelloWorldView.class);
                }
    }
    
    

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            //setOpened(false);
            event.forwardTo("");
        }

        //setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}
