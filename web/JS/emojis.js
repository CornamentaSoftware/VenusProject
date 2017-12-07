var bool = true;
var ver = true;

function emoji(e){
    document.getElementById("textArea").innerHTML+=e;
}
function vin(num){
    document.getElementById(num).style.display="table";
    for(i=1;i<num;i++){
        document.getElementById(i).style.display="none";
    }
    for(i=(num+1);i<14;i++){
        document.getElementById(i).style.display="none";
    }
    
}
function emojis(){
    if(bool){
        document.getElementById("todosE").style.display="table";
        document.getElementById("todaspest").style.display="table";
        document.getElementById("1").style.display="table";
        bool = false;
    }
    else{
        document.getElementById("todaspest").style.display="none";
        document.getElementById("todosE").style.display="none";
        for(i=1;i<14;i++){
            document.getElementById(i).style.display="none";
        }
        bool = true;
    }
}

