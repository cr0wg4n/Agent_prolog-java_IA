lugar(casa).
lugar(mercado).
presupuesto_minimo(39).
parte_de_receta(harina).
parte_de_receta(azucar).
parte_de_receta(huevo).
parte_de_receta(vainilla).
parte_de_receta(mantequilla).
parte_de_receta(levadura).
parte_de_receta(leche).
precio(harina,10).
precio(azucar,5).
precio(huevo,6).
precio(vainilla,4).
precio(mantequilla,5).
precio(leche,6).
precio(levadura,3).
se_mezcla_con(huevo,vainilla).
se_mezcla_con(harina,levadura).
batir_paso2(X,Y):- X==huevo,Y==vainilla.
comprar(X,Z):- precio(X,Y),Y<=Z.
recibir_dinero(X):- X>=39.




