function cambia(){
    var valor = document.getElementById("imagen").value;
    document.getElementById("texto").innerHTML=valor;
}
function cambiaPerfil(){
    var valor = document.getElementById("imagen").value;
    document.getElementById("texto").innerHTML=valor;
    document.getElementById("foto").style.fontSize="16px";
    document.getElementById("imagen").style.width="auto";
    document.getElementById("imagen").style.marginLeft="-125px";
}


