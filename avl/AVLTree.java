// Q1 - RESPOSTA ESTÁ NO ARQUIVO MAIN
// Q2 - ESTRATÉGIA PARA RESOLUÇÃO
//   a. Foram inseridos 15 itens na seguinte ordem, de modo que a arvore ficasse cheia:
//      64, 32, 82, 16, 48, 76, 90, 14, 18, 40, 58, 70, 80, 86, 99
//   b. Foram inseridos 15 itens na seguinte ordem, de modo que a arvore ficasse cheia:
//      60, 56, 58, 59, 57, 54, 55, 53, 64, 66, 62, 63, 61, 65, 67
//   c. Foram inseridos 13 itens na seguinte ordem, assim o ultimo nível não ficou cheio:
//      20, 17, 18, 19, 30, 25, 26, 13, 15, 21, 50, 12, 48
//   d. Foram inseridos 11 itens na seguinte ordem, assim o ultimo nível não ficou cheio:
//      31, 65, 21, 23, 29, 78, 52, 12, 15, 69, 79
//   e. Foram inseridos 12 itens na seguinte ordem:
//      51, 32, 77, 22, 44, 72, 97, 25, 42, 45, 64, 49
//		Para ser uma arvore AVL não completa, a arvore está com altura 5, e o nó 97, no nível 3, é um nó folha
//   f. Foram inseridos 15 itens na seguinte ordem:
//      52, 23, 67, 15, 31, 61, 91, 12, 28, 36, 60, 25, 30, 33, 48
//		Para ser uma arvore AVL não completa, a arvore está com altura 5, e o nó 91, no nível 3, é um nó folha

package avl;

public class AVLTree extends BinarySearchTree{

    public String toTreeView(){
        if(this.isEmpty()) return "Esta arvore eh vazia!";
        return "\n" + root.subTreeString("");
    }

    private void updateNodeHeight(Node no){
        if(no == null) return;
        int he = no.hasLeft() ? no.left.height : 0;
        int hd = no.hasRight() ? no.right.height : 0;
        no.height = 1 + Math.max(hd, he);
    }

    private int getSubTreeHeightDif(Node no){
        if(no == null) return 0;
        int he = no.hasLeft() ? no.left.height : 0;
        int hd = no.hasRight() ? no.right.height : 0;
        return he - hd;
    }

    private Node leftRotation(Node no){
        if(no == null) return no;
        if(!no.hasRight()) return no;
        Node aux = no.right;
        no.right = aux.left;
        aux.left = no;
        updateNodeHeight(no);
        updateNodeHeight(aux);
        return aux;
    }

    private Node rightRotation(Node no){
        if(no == null) return no;
        if(!no.hasLeft()) return no;
        Node aux = no.left;
        no.left = aux.right;
        aux.right = no;
        updateNodeHeight(no);
        updateNodeHeight(aux);
        return aux;
    }

    private Node addAVL(Node no, int value){
        if(no == null){
            no = new Node(value);
            no.height = 1;
            return no;
        }
        if(no.value == value){
            return no;
        } 
        if(value > no.value){
            no.right = addAVL(no.right, value); // caso o novo valor seja maior, será inserido na subarvore direita
        } else {
            no.left = addAVL(no.left, value); // caso o novo valor seja menor, será inserido na subarvore esquerda
        }
        updateNodeHeight(no);
        int dif = getSubTreeHeightDif(no);
        if(dif > 1){
            if(getSubTreeHeightDif(no.left) < 0){
                no.left = leftRotation(no.left);
            }
            return rightRotation(no);
        } else if (dif < -1) {
            if(getSubTreeHeightDif(no.right) > 0){
                no.right = rightRotation(no.right);
            }
            return leftRotation(no);
        }
        return no;
    }

    public boolean addAVL(int value){
        if(this.isEmpty()){
            this.add(value); // para a criação do primeiro nó, usamos o método padrão da arvore binária
        } else {
            this.root = this.addAVL(this.root, value);
        }
        return true;
    }

}