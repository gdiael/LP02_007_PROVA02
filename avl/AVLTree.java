// Q1 - RESPOSTA ESTÁ NO ARQUIVO MAIN
//
// Q2 - ESTRATÉGIA PARA RESOLUÇÃO - ROTAÇÃO A ESQUERDA
//
// Para a execução da rotação à esquerda de uma sub arvore qualquer foi criado um método com assinatura
// "private Node leftRotation(Node no)"
// nesse método será passado o nó que servirá de pixô para a rotação
// foi verificado se o nó e seu filho a direita (que susbstituirá sua posição) não são nulos, caso sejam,
// será retornada a referência para o próprio nó.
// a execução do método segue quatro passos simples
//   1 - a referência para o nó a direita no nó pivô é guardada numa variável auxiliar (a seguir esse nó será chamado de nó auxiliar)
//   2 - o nó a direita do nó pivô aponta para o nó a esquerda do nó auxiliar
//   3 - o nó pivô agora será o novo filho a esquerda do nó auxiliar
//   4 - as alturas dos nós são atualizadas
// com esse passos vemos que o nó auxiliar, que estava a direita do nó pivô agora é a raiz dessa subarvore
// logo é necessário atualizar a referência que apontava para o nó pivô, para tanto esse método faz o retorno do nó
// auxiliar e no código que faz a chamada desse método é necessário apontar o retorno desse método para a antiga
// referência que apontava para o nó pivô.
//
// Q3 - ESTRATÉGIA PARA RESOLUÇÃO - ROTAÇÃO A DIREITA
// 
// Para executar a rotação a direita foram seguidos os mesmos passos do método de rotação a esquerda, porém de forma espelhada,
// ou seja, o nó auxiliar nesse caso será o nó a esquerda do nó pivô e após a rotação o nó pivô será o filho a direita
// do nó auxiliar, da mesma forma a referência para o nó auxiliar deverá ser retornada pelo método para ser utilizada
// na atualização da referência que apontava para o nó pivô
//
// Q4 - ESTRATÉGIA PARA RESOLUÇÃO - INSERÇÃO NA ARVORE AVL
//
// Para a inserção na arvore foi criado um método público com a assinatura:
// "public boolean addAVL(int value)"
// nesse método o valor que será inserido na AVL é passo como parâmetro
// internamente ele faz a chamda do método priva e recursivo passando a raiz como parâmetro:
// "private Node addAVL(Node no, int value)"
// esse método percorre a arvore de forma recursiva até que seja direcionado para um nó nulo
// quando encontra o nó nulo, que é sua condição de parada, irá criar um novo nó com o valor passado
// e retornará o novo nó, ao ir saindo das chamadas recursovas irá atualizar as alturas das subarvores
// e caso algura subarvore fique desbaçanceada após a inserção (o módulo da diferência entre as alturas seja maior que 1) 
// ele chamará os métodos de rotação a direita ou a esquerda para executar o balanceamento de forma correta.
//
// Q5 - ESTRATÉGIA PARA RESOLUÇÃO - REBALANCEAR
// 
// Um método privado primeiro percorre os nós da arvore binária no formado inOrder e preenche um Vector com esses nó
// Desse modo sabemos que os nós estão organizados no Vector em ordem cescente.
// um segundo método percorre esse vector usando uma abordagem de dividir e conquistar de forma recursiva.
// calcula-se o indice do meio de um intervalo (no primeiro passo é o Vector inteiro), com esse indice acessamos a referência
// ao nó, que será nosso nós raiz, divide-se o intervalo em dois e são percorridos de forma recursiva, o retorno do método
// em cada um desses intervalos será nossa subarvore a direita e a esquerda.

package avl;

import java.util.Vector;

public class AVLTree extends BinarySearchTree{

    public String toTreeView(){
        if(this.isEmpty()) return "Esta arvore eh vazia!";
        return "\n" + root.subTreeString("");
    }

    private static void updateNodeHeight(Node no){
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
        this.root = this.addAVL(this.root, value);
        return true;
    }

    private static void BinaryTreeInOrderScan(Node no, Vector<Node> vec){
        if(no != null){
            BinaryTreeInOrderScan(no.left, vec);
            vec.add(no);
            BinaryTreeInOrderScan(no.right, vec);
        }
    }

    private static Node reorganizeNodes(int ini, int end, Vector<Node> vec){
        if(ini > end) return null;
        int mid = (ini + end)/2;
        Node no = vec.get(mid);
        no.left = reorganizeNodes(ini, mid-1, vec);
        no.right = reorganizeNodes(mid+1, end, vec);
        updateNodeHeight(no);
        return no;
    }

    public static AVLTree convert(BinarySearchTree tree){
        Vector<Node> vec = new Vector<Node>();
        BinaryTreeInOrderScan(tree.root, vec);
        AVLTree avl = new AVLTree();
        avl.root = reorganizeNodes(0, vec.size()-1, vec);
        return avl;
    }

}