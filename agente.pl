en(casa).
en(tienda).
en(cocina).

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

se_mezcla_con(harina,levadura,bol_2).
se_mezcla_con(mantequilla,azucar,bol_1).

agregar(bol_1,X,Y):- X==huevo,Y==mantequilla.
agregar(bol_2,X):- X==leche.
agregar(bandeja,X):- X==capucillos.

calentar(horno,X):- X==160.

comprar(X,Z):- precio(X,Y),Y<Z.

recibir_dinero(X):- X>=39.

tener(X, [X|_]).
tener(X,[_|Ys]):- tener(X,Ys).

hornear(masa,X):- X==15.

