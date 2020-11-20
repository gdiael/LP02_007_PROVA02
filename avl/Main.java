// Q1 - ESTRATÉGIA PARA RESOLUÇÃO
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
// 
// DESCRIÇÃO DAS DEMAIS QUESTÕES ESTÃO NO ARQUIVO AVLTree
//
// a impressão da arvore segue o esquema
//  valor ┬─> [direita ]
//        └─> [esquerda]

package avl;

import java.io.BufferedReader;
import java.io.FileReader;

class PrintVisitor implements Visitor {
	@Override
	public void visit(int element, int height) {
		System.out.print( element + " ["+height+"]");
	}
}

public class Main {	

	public static void main(String[] args) {
		// Visitor visitor = new PrintVisitor();
		// Q1 Letra a
		AVLTree tree1 = new AVLTree();
		int[] vec1 = {64, 32, 82, 16, 48, 76, 90, 14, 18, 40, 58, 70, 80, 86, 99};
        for(int i : vec1){
            tree1.add(i);
        }
		// tree1.preOrder(visitor);
		System.out.print(tree1.toTreeView());
		System.out.println("Q1 a - Tree1 - Cheia? " + Boolean.toString(tree1.isFull()));
		// Q2 Letra b
		AVLTree tree2 = new AVLTree();
		int[] vec2 = {60, 56, 58, 59, 57, 54, 55, 53, 64, 66, 62, 63, 61, 65, 67};
        for(int i : vec2){
            tree2.add(i);
        }
		System.out.print(tree2.toTreeView());
		System.out.println("Q1 b - Tree2 - Cheia? " + Boolean.toString(tree2.isFull()));
		// Q1 Letra c
		AVLTree tree3 = new AVLTree();
		int[] vec3 = {20, 17, 18, 19, 30, 25, 26, 13, 15, 21, 50, 12, 48};
        for(int i : vec3){
            tree3.add(i);
        }
		System.out.print(tree3.toTreeView());
		System.out.println("Q1 c - Tree3 - Completa? " + Boolean.toString(tree3.isComplete()) + " | Cheia? " + Boolean.toString(tree3.isFull()));
		// Q1 Letra d
		AVLTree tree4 = new AVLTree();
		int[] vec4 = {31, 65, 21, 23, 29, 78, 52, 12, 15, 69, 79};
        for(int i : vec4){
            tree4.add(i);
        }
		System.out.print(tree4.toTreeView());
		System.out.println("Q1 d - Tree4 - Completa? " + Boolean.toString(tree4.isComplete()) + " | Cheia? " + Boolean.toString(tree4.isFull()));
		// Q1 Letra e
		AVLTree tree5 = new AVLTree();
		int[] vec5 = {51, 32, 77, 22, 44, 72, 97, 25, 42, 45, 64, 49};
        for(int i : vec5){
            tree5.add(i);
        }
		System.out.print(tree5.toTreeView());
		System.out.println("Q1 e - Tree5 - AVL? " + Boolean.toString(tree5.isAVL()) + " | Completa? " + Boolean.toString(tree5.isComplete()));
		// Q1 Letra f
		AVLTree tree6 = new AVLTree();
		int[] vec6 = {52, 23, 67, 15, 31, 61, 91, 12, 28, 36, 60, 25, 30, 33, 48};
        for(int i : vec6){
            tree6.add(i);
        }
		System.out.print(tree6.toTreeView());
		System.out.println("Q1 f - Tree6 - AVL? " + Boolean.toString(tree6.isAVL()) + " | Completa? " + Boolean.toString(tree6.isComplete()));
		// Q4 teste simples
		AVLTree tree7 = new AVLTree();
		int[] vec7 = {45, 50, 55, 51, 52, 40, 35, 30, 34, 31};
        for(int i : vec7){
            tree7.addAVL(i);
        }
		System.out.print(tree7.toTreeView());
		System.out.println("Q4 - Tree7 - AVL? " + Boolean.toString(tree7.isAVL()));
		// Q5 teste simples
		BinarySearchTree tree8 = new BinarySearchTree();
		int[] vec8 = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        for(int i : vec8){
            tree8.add(i);
        }
		System.out.println("\n" + tree8.root.subTreeString(""));
		System.out.println("Q5 - Tree8 - AVL? " + Boolean.toString(tree8.isAVL()));
		AVLTree tree9 = AVLTree.convert(tree8);
		System.out.print(tree9.toTreeView());
		System.out.println("Q5 - Tree9 - AVL? " + Boolean.toString(tree9.isAVL()));

		try {
			BufferedReader input = new BufferedReader(new FileReader("q4.in"));
			BufferedReader output = new BufferedReader(new FileReader("q4.out"));
			String line = input.readLine();
			while(line != null) {
				String [] split = line.split(" ");
				AVLTree tree = new AVLTree(); // substituir pela implementação da árvore AVL
				for(String s : split) {
					int value = Integer.parseInt(s);
					tree.addAVL(value); // aqui foi substituído pelo método que foi implementado
				}
				String result = output.readLine();
				if(!result.equals(tree.toString())) {
					System.out.println(result + " != " + tree + "\n");
				}
				
				line = input.readLine();
			}
			input.close();
			output.close();
		} catch (Exception e) {
			System.out.println("Erro!\n" + e);
		}

	}
}
