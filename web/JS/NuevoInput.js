var cont=1;
var cont2=1;
function masMateriales(){
    cont++;
    var micapa = document.getElementById('Materiales');
    micapa.innerHTML+='<input type="text" class="R" placeholder="Material '+cont+'" name="username">';
}
function menosMateriales(){
    var micapa = document.getElementById('Materiales');
    if(cont!==1){
    cont--;
    }
    micapa.innerHTML='<input type="text" class="R" placeholder="Material 1" name="username">';
    for(i=1;i<cont;i++){
        micapa.innerHTML+='<input type="text" class="R" placeholder="Material '+(i+1)+'" name="username">';
    }
}
function masMultimedia(){
    cont2++;
    var micapa = document.getElementById('Multimedia');
    micapa.innerHTML+='<span class="iborrainputfile" id="valor"><i class="fa fa-upload subir"></i><p class="nada" id="texto">Seleccionar multimedia</p><input type="file" name="fichero" accept=".jpg, .jpeg, .png, .mp4" id="imagen" onchange="cambia();" class="inputfile" required=""/></span>';
}
function menosMultimedia(){
    var micapa = document.getElementById('Multimedia');
    if(cont2!==1){
    cont2--;
    }
    micapa.innerHTML='<span class="iborrainputfile" id="valor"><i class="fa fa-upload subir"></i><p class="nada" id="texto">Seleccionar multimedia</p><input type="file" name="fichero" accept=".jpg, .jpeg, .png, .mp4" id="imagen" onchange="cambia();" class="inputfile" required=""/></span>';
    for(i=1;i<cont2;i++){
        micapa.innerHTML+='<span class="iborrainputfile" id="valor"><i class="fa fa-upload subir"></i><p class="nada" id="texto">Seleccionar multimedia</p><input type="file" name="fichero" accept=".jpg, .jpeg, .png, .mp4" id="imagen" onchange="cambia();" class="inputfile" required=""/></span>';
    }
}
var cont3=1;
function masEtiquetas(){
    cont3++;
    var micapa = document.getElementById('Etiquetas');
    micapa.innerHTML+='<input type="text" class="R" placeholder="Etiqueta '+cont3+'" name="username">';
}
function menosEtiquetas(){
    var micapa = document.getElementById('Etiquetas');
    if(cont3!==1){
    cont3--;
    }
    micapa.innerHTML='<input type="text" class="R" placeholder="Etiqueta 1" name="username">';
    for(i=1;i<cont3;i++){
        micapa.innerHTML+='<input type="text" class="R" placeholder="Etiqueta '+(i+1)+'" name="username">';
    }
}

