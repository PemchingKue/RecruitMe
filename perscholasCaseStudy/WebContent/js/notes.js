//cookie function

function createCookie(name, value, days) {
    var expires = '';
    var date = new Date();
    if (days) {
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = '; expires=' + date.toGMTString();
    }
    document.cookie = name + '=' + value + expires + '; path=/';
    var test = name + '=' + value + expires + '; path=/';
}

function readCookie(name) {
    var nameEQ = name + '=';
    var allCookies = document.cookie.split(';');
    var i;
    var cookie;

    for (i = 0; i < allCookies.length; i += 1) {
        cookie = allCookies[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1, cookie.length);
        }
        cookie = decodeURIComponent(cookie);
        if (cookie.indexOf(nameEQ) === 0) {
            return cookie.substring(nameEQ.length, cookie.length);
        }
    }
}

function eraseCookie(name) {
    createCookie(name, '', -1);
}

//notes
var notes = document.querySelector(".notes");

if(notes != null){
    console.log(notes);
}else{
    console.log("value set is null");
}

window.addEventListener("load", function (evt) {
    notes.innerHTML = readCookie("notes");
});

notes.addEventListener("change", function (evt) {
    eraseCookie("notes");
    createCookie("notes", encodeURIComponent(notes.value), 0);
});



