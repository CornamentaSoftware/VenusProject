var bool = true;

function emoji(e){
    document.getElementById("textArea").innerHTML+=e;
}
function emojis(){
    if(bool){
        document.getElementById("todosE").style.display="table";
        bool = false;
    }
    else{
        document.getElementById("todosE").style.display="none";
        bool = true;
    }
}

