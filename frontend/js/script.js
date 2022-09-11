
var elemen;

window.setroot = function (elem){
	elemen = elem;
}

window.onTelegramAuth = function onTelegramAuth(user) {
    elemen.$server.enterApp(user.id, user.first_name, user.last_name, user.username, user.photo_url, user.auth_date, user.hash);
    }
    
window.greet = function greet(name, element) {
    console.log("Hi, " + name);
    element.$server.greet("server");
}

window.greeting = function greeting(name) {
    console.log("Hi, " + name);
    elemen.$server.greet("server auth");
}


    