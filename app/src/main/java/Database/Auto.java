package Database;

 public class Auto {

    String idAmigos;
    String marcayModelo;
    String caracteristicas;
    String requisitos;
    String caja;
    String precio;
    String urlImg;

     public String getIdAmigos() {
         return idAmigos;
     }

     public void setIdAmigos(String idAmigos) {
         this.idAmigos = idAmigos;
     }

     public String getMarcayModelo() {
         return marcayModelo;
     }

     public void setMarcayModelo(String marcayModelo) {
         this.marcayModelo = marcayModelo;
     }

     public String getCaracteristicas() {
         return caracteristicas;
     }

     public void setCaracteristicas(String caracteristicas) {
         this.caracteristicas = caracteristicas;
     }

     public String getRequisitos() {
         return requisitos;
     }

     public void setRequisitos(String requisitos) {
         this.requisitos = requisitos;
     }

     public String getCaja() {
         return caja;
     }

     public void setCaja(String caja) {
         this.caja = caja;
     }

     public String getPrecio() {
         return precio;
     }

     public void setPrecio(String precio) {
         this.precio = precio;
     }

     public String getUrlImg() {
         return urlImg;
     }

     public void setUrlImg(String urlImg) {
         this.urlImg = urlImg;
     }

     public Auto(String idAmigos, String marcayModelo, String caracteristicas, String requisitos, String caja, String precio, String urlImg) {
         this.idAmigos = idAmigos;
         this.marcayModelo = marcayModelo;
         this.caracteristicas = caracteristicas;
         this.requisitos = requisitos;
         this.caja = caja;
         this.precio = precio;
         this.urlImg = urlImg;
     }

}
