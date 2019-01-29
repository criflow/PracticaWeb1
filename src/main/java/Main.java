
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

    private static Document document;
    public static void main(String[] args) {

        System.out.println("Favor digitar URL: ");
        Scanner scanner = new Scanner(System.in);

        String direccion = scanner.nextLine();

        try{
            document = Jsoup.parse(url);

            document = Jsoup.connect(url).get();

            Connection.Response doc = Jsoup.connect(url).execute();
            cuerpo = doc.body();

            int cant_img;

            Elements elements = document.select("p img");
            cant_img = elements.size();

            Elements elements = document.select("p");
            int cant_parrafos = elements.size();
            System.out.println("Cantidad de lineas: " +   cuerpo.split("\n").length));
            System.out.println("Cantidad de parrafos: " + cant_parrafos);
            System.out.println("Cantidad de imagenes: " + cant_img);

            try {
                cantidadFormularios(direccion);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch(Exception ex) {
            System.out.println("Error:");
            ex.toString();
        }
    }




    private static void cantidadFormularios(String url) throws IOException {

        int  cantPost, cantGet, form=1;

        Elements formElement = document.select("[method=post]");
        cantPost = formElement.size();
        System.out.println("Formularios con el metodo POST: " + cantPost);

        formElement = document.select("[method=get]");
        cantGet = formElement.size();
        System.out.println("Formularios con el metodo GET: " + cantGet);

        
        for (Element element: document.getElementsByTag("form").forms()) {
            String metodo = element.attr("method");
            Elements tipoPost = element.getElementsByAttributeValueContaining("method", "post");


            for (Element el: tipoPost ) {

                String dir = el.absUrl("action");
                try{
                    System.out.println("Formulario: #"+form);
                    Document document1 = Jsoup.connect(dir)
                            .data("asignatura","practica1")
                            .header("matricula","2015-1256").post();

                    System.out.println(document1.body().toString());
                }catch (HttpStatusException e){

                }
            }



            Elements inputs = element.select("input");
            for (Element element1: inputs) {
                System.out.println("Tipo: " + element1.attr("type"));
            }

            System.out.println("");
            form++;
        }
    }


}
