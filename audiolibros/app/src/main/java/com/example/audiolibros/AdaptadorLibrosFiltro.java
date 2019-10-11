package com.example.audiolibros;

import android.content.Context;

import java.util.Vector;

public class AdaptadorLibrosFiltro extends AdaptadorLibros {
    private Vector<Libro> vectorSinFiltro; //Vector con todos los filtros
    private Vector<Integer> indiceFiltro;

    private String busqueda = "";
    private String genero = "";
    private boolean novedad = false;
    private boolean leido = false;

    public AdaptadorLibrosFiltro(Context contexto, Vector<Libro> vectorLibros) {
        super(contexto, vectorLibros);
        vectorSinFiltro = vectorLibros;
        recalcularFiltro();
    }

    public void setBusqueda(String busqueda){
        this.busqueda = busqueda.toLowerCase();
        recalcularFiltro();
    }

    public void setGenero(String genero){
        this.genero = genero;
        recalcularFiltro();
    }

    public void setNovedad  (boolean novedad){
        this.novedad = novedad;
        recalcularFiltro();
    }

    public void setLeido (boolean leido){
        this.leido = leido;
        recalcularFiltro();
    }

    private void recalcularFiltro() {
        vectorLibros = new Vector<Libro>();
        indiceFiltro = new Vector<Integer>();

        for (int i = 0; i < vectorSinFiltro.size(); i++){
            Libro libro = vectorSinFiltro.elementAt(i);
                if ((libro.titulo.toLowerCase().contains(busqueda)||
                        libro.autor.toLowerCase().contains(busqueda))
                        && (libro.genero.startsWith(genero))
                        && (!novedad || (novedad && libro.leido))){
                    vectorLibros.add(libro);
                    indiceFiltro.add(i);
                }
        }
    }


    public Libro getItem(int posicion){
        return vectorSinFiltro.elementAt(indiceFiltro.elementAt(posicion));
    }

    public long getItemId(int posicion){
        return indiceFiltro.elementAt(posicion);
    }

    public void borrar (int posicion){
        vectorSinFiltro.remove((int) getItemId(posicion));
        recalcularFiltro();
    }

    public void insertar(Libro libro){
        vectorSinFiltro.add(libro);
        recalcularFiltro();
    }

}
