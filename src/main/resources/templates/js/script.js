function setStyles() {
    var btn=document.getElementsByTagName('button');
    for(let i=0 ; i<btn.length ; i++) {
        btn[i].style.margin="13px";
    }
}

window.addEventListener('load', setStyles());