
package arbolBB;

  /**
 *
 * @author Toloza XD
 */

import entities.AVLNode;
import entities.BinaryTree;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javafx.scene.shape.Circle;
import javax.swing.*;


public class ArbolExpresionGrafico extends JPanel 
{
    private BinaryTree tree;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 60, child2child = 70;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param miExpresion: dato de tipo ArbolExpresion que contiene el Arbol a
     * dibujar.
     */
    public ArbolExpresionGrafico(BinaryTree miArbol) 
    {
          this.tree = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         AVLNode root = tree.getRoot();
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension calcularTamañoSubarbol(AVLNode n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = calcularTamañoSubarbol(n.left);
          Dimension rd = calcularTamañoSubarbol(n.right);
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
    
    /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la información específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo NodoB <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    private void calcularPosicion(AVLNode n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.left);
      if (ld == null) 
          ld = empty;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.right);
      if (rd == null) 
          rd = empty;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(n.item+"");
       Shape theCircle = new Ellipse2D.Double(center - width/2-3, top,width + 12,fm.getHeight() + 4); 
      posicionNodos.put(n,theCircle);
      
      calcularPosicion(n.left, Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.right, center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
    
    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics2D g, AVLNode n, int puntox, int puntoy, int yoffs, AVLNode h) 
    {
     if (n == null) 
         return;
     
     Shape r = (Shape) posicionNodos.get(n);
     g.setPaint(Color.BLUE);
     g.draw(r);
     g.setPaint(Color.BLACK);
     g.drawString(" " + n.item+" ", r.getBounds().x + 3, r.getBounds().y + yoffs + 3);
     if (puntox != Integer.MAX_VALUE){
         g.setPaint(Color.red);
         int num = (n.compareTo(h)<=0) ? -10 : 10 ; 
         g.drawLine(puntox + num, puntoy, (int)(r.getBounds().x - num/5 + r.getBounds().width/2) , r.getBounds().y);
     }
     dibujarArbol(g, n.left, (int)(r.getBounds().x + r.getBounds().width/2), r.getBounds().y + r.getBounds().height, yoffs,n);
     dibujarArbol(g, n.right, (int)(r.getBounds().x + r.getBounds().width/2), r.getBounds().y + r.getBounds().height, yoffs,n);
     
   }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty) 
         {
           calcularPosiciones();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent(),null);
         fm = null;
   }
   
 }




