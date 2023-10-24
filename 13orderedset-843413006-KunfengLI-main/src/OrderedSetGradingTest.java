/*
 * A JUnit test for OrderedSet
 * 
 * @author Rick Mercer
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderedSetGradingTest {

	@Test
	public void testInsertSizeAndTostringInorder() {
		OrderedSet<String> aBST = new OrderedSet<String>();
		aBST.insert("Al");
		aBST.insert("Di");
		aBST.insert("Cy");
		aBST.insert("Li");
		aBST.insert("Di");
		aBST.insert("Me");

		OrderedSet<String> bst = new OrderedSet<String>();
		assertEquals("", bst.toStringInorder());
		assertTrue(bst.insert("d"));
		assertTrue(bst.insert("b"));
		assertTrue(bst.insert("e"));
		assertFalse(bst.insert("e"));
		assertTrue(bst.insert("a"));
		assertEquals(4, bst.size());
		assertEquals("a b d e", bst.toStringInorder());
	}

	@Test
	public void testInsertWithSizeWhenDuplicatesAreAttempted() {
		OrderedSet<String> bst = new OrderedSet<String>();
		assertEquals(0, bst.size());
		bst.insert("d");
		assertEquals(1, bst.size());
		bst.insert("b");
		assertEquals(2, bst.size());
		bst.insert("b");
		assertEquals(2, bst.size());
		bst.insert("d");
		assertEquals(2, bst.size());
		bst.insert("a");
		assertEquals(3, bst.size());
	}

	@Test
	public void testContainsWhenShouldReturnTrue() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("d");
		bst.insert(new String("b"));
		bst.insert("e");
		bst.insert("f");
		bst.insert(new String("a"));
		bst.insert("c");
		assertTrue(bst.contains("a"));
		assertTrue(bst.contains("b"));
		assertTrue(bst.contains("c"));
		assertTrue(bst.contains("d"));
		assertTrue(bst.contains("e"));
		assertTrue(bst.contains("f"));
	}

	@Test
	public void testContainsWhenShouldReturnFalse() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("d");
		bst.insert("b");
		bst.insert("e");
		bst.insert("f");
		bst.insert("a");
		bst.insert("c");
		assertFalse(bst.contains("?"));
		assertFalse(bst.contains("X"));
		assertFalse(bst.contains("A"));
	}

	@Test
	public void testMaxWhenEmpty() {
		OrderedSet<Integer> intTree = new OrderedSet<Integer>();
		assertNull(intTree.max());
	}

	@Test
	public void testMaxWhenSizeIsOne() {
		OrderedSet<Integer> intTree = new OrderedSet<Integer>();
		intTree.insert(3);
		assertEquals(3, intTree.max());
	}

	@Test
	public void testMaxAgain() {
		OrderedSet<Integer> intTree = new OrderedSet<Integer>();
		assertNull(intTree.max());
		intTree.insert(7);
		assertEquals(7, intTree.max());
		intTree.insert(5);
		assertEquals(7, intTree.max());
		intTree.insert(8);
		intTree.insert(-1);
		assertEquals(8, intTree.max());
	}

	@Test
	public void testMin() {
		OrderedSet<Integer> intTree = new OrderedSet<Integer>();
		assertNull(intTree.min());
		intTree.insert(5);
		assertEquals(5, intTree.min());
		intTree.insert(8);
		assertEquals(5, intTree.min());
		intTree.insert(-1);
		intTree.insert(4);
		assertEquals(-1, intTree.min());
	}

	@Test
	public void testIntersectionWhenBothAreEmpty() {
		OrderedSet<String> bst = new OrderedSet<String>();
		OrderedSet<String> bst2 = new OrderedSet<String>();
		OrderedSet<String> result = bst.intersection(bst2);
		assertEquals(0, result.size());
	}

	@Test
	public void testIntersectionWhenOneIsEmpty() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		bst.insert("N");
		bst.insert("O");
		bst.insert("P");
		bst.insert("A");
		OrderedSet<String> bst2 = new OrderedSet<String>();
		OrderedSet<String> result = bst.intersection(bst2);
		assertEquals(0, result.size());
	}

	@Test
	public void testIntersectionWhenBothHaveNoneTheSameElements() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		bst.insert("N");
		bst.insert("O");
		bst.insert("P");
		bst.insert("A");
		OrderedSet<String> bst2 = new OrderedSet<String>();
		bst2.insert("Qq");
		bst2.insert("Nw");
		bst2.insert("Pe");
		bst2.insert("Ar");
		bst2.insert("Rt");

		OrderedSet<String> result = bst.intersection(bst2);

		assertEquals(0, result.size());
	}

	@Test
	public void testIntersectionWhenBothHaveThreeOfTheSameElements() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		bst.insert("N");
		bst.insert("O");
		bst.insert("P");
		bst.insert("A");
		OrderedSet<String> bst2 = new OrderedSet<String>();
		bst2.insert("Q");
		bst2.insert("N");
		bst2.insert("P");
		bst2.insert("A");
		bst2.insert("R");

		OrderedSet<String> result = bst.intersection(bst2);

		assertEquals(3, result.size());
		assertTrue(bst.contains("N"));
		assertTrue(bst.contains("P"));
		assertTrue(bst.contains("A"));

	}

	@Test
	public void testIntersectionWhenBothHaveTheSameElements() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		bst.insert("N");
		bst.insert("O");
		bst.insert("P");
		bst.insert("A");
		OrderedSet<String> bst2 = new OrderedSet<String>();
		bst2.insert("M");
		bst2.insert("N");
		bst2.insert("P");
		bst2.insert("A");
		bst2.insert("O");

		OrderedSet<String> result = bst.intersection(bst2);

		assertEquals(5, result.size());
		assertTrue(bst.contains("M"));
		assertTrue(bst.contains("N"));
		assertTrue(bst.contains("P"));
		assertTrue(bst.contains("A"));
		assertTrue(bst.contains("O"));

	}

	@Test
	public void testUnionWhenBothAreEmpty() {
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		OrderedSet<Integer> b = new OrderedSet<Integer>();
		OrderedSet<Integer> c = a.union(b);
		assertEquals(0, c.size());
	}

	@Test
	public void testUnion() {
		// The union of {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		a.insert(2);
		a.insert(4);
		a.insert(6);

		OrderedSet<Integer> b = new OrderedSet<Integer>();
		b.insert(2);
		b.insert(5);
		b.insert(9);

		OrderedSet<Integer> c = a.union(b);
		assertEquals(5, c.size());
		assertTrue(c.contains(2));
		assertTrue(c.contains(4));
		assertTrue(c.contains(5));
		assertTrue(c.contains(6));
		assertTrue(c.contains(9));
	}

	@Test
	public void testUnionWhenOneIsEmpty() {
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		a.insert(2);
		a.insert(4);
		a.insert(6);

		OrderedSet<Integer> b = new OrderedSet<Integer>();

		OrderedSet<Integer> c = a.union(b);
		assertEquals(3, c.size());
		assertTrue(c.contains(2));
		assertTrue(c.contains(4));
		assertTrue(c.contains(6));
	}

	@Test
	public void testUnionWhenTheOtherIsEmpty() {
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		OrderedSet<Integer> b = new OrderedSet<Integer>();
		b.insert(2);
		b.insert(5);
		b.insert(9);

		OrderedSet<Integer> c = a.union(b);
		assertEquals(3, c.size());
		assertTrue(c.contains(2));
		assertTrue(c.contains(5));
		assertTrue(c.contains(9));
	}

	@Test
	public void testUnionWhenBothSetsAreEqual() {
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		a.insert(2);
		a.insert(5);
		a.insert(9);

		OrderedSet<Integer> b = new OrderedSet<Integer>();
		b.insert(2);
		b.insert(5);
		b.insert(9);

		OrderedSet<Integer> c = a.union(b);
		assertEquals(3, c.size());
		assertTrue(c.contains(2));
		assertTrue(c.contains(5));
		assertTrue(c.contains(9));
	}

	@Test
	public void testUnionWhenBothSetsAreUnique() {
		OrderedSet<Integer> a = new OrderedSet<Integer>();
		a.insert(2);
		a.insert(4);
		a.insert(6);

		OrderedSet<Integer> b = new OrderedSet<Integer>();
		b.insert(1);
		b.insert(3);
		b.insert(5);
		b.insert(7);
		b.insert(8);

		OrderedSet<Integer> c = a.union(b);
		assertEquals(8, c.size());
		assertTrue(c.contains(1));
		assertTrue(c.contains(2));
		assertTrue(c.contains(3));
		assertTrue(c.contains(4));
		assertTrue(c.contains(5));
		assertTrue(c.contains(6));
		assertTrue(c.contains(7));
		assertTrue(c.contains(8));
	}

	@Test
	public void testContainsAsTreeIsGrowing() {
		OrderedSet<String> aTree = new OrderedSet<String>();
		assertFalse(aTree.contains("M"));
		aTree.insert("M");
		assertTrue(aTree.contains("M"));

		assertFalse(aTree.contains("E"));
		aTree.insert("E");
		assertTrue(aTree.contains("E"));

		aTree.insert("A");
		aTree.insert("G");
		aTree.insert("H");
		aTree.insert("F");
		aTree.insert("D");
		aTree.insert("C");

		assertTrue(aTree.contains("A"));
		assertTrue(aTree.contains("G"));
		assertTrue(aTree.contains("F"));
		assertTrue(aTree.contains("H"));
		assertTrue(aTree.contains("D"));
		assertTrue(aTree.contains("C"));
	}

	// These assertions should pass
	@Test
	public void testSubSet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		assertEquals("12 25", bst.subset(1, 49).toStringInorder());
	}

	@Test
	public void testSubSetWhenExclusiveIsInTheSet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("12 25 50 65 75 90", bst.toStringInorder());
		assertEquals("25 50 65", bst.subset(25, 75).toStringInorder());
	}

	@Test
	public void testSubSetWhenTheResultShouldBeAnEmptySet() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(12);
		bst.insert(75);
		bst.insert(65);
		bst.insert(90);
		assertEquals("", bst.subset(12, 12).toStringInorder()); // 12 < 12 is false
	}

	@Test
	public void testTestingRemoveWhenBSTIsEmpty() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertFalse(bst.remove(50));
	}

	// Test remove
	@Test
	public void testRemoveWhenBSTIsEmpty() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		assertFalse(bst.remove(50));
	}

	@Test
	public void testRemoveWhenElementIsNotInTreeWithFiveNodes() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(75);
		bst.insert(7);
		bst.insert(95);
		assertFalse(bst.remove(51));
		assertFalse(bst.remove(-1));
		assertFalse(bst.remove(51));
		assertFalse(bst.remove(97));
	}

	@Test
	public void testRemoveRightNodeWhenSizeIsTwo() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(75);
		bst.remove(75);
		assertEquals("50", bst.toStringInorder());
	}

	@Test
	public void testRemoveLeftNodeWhenTreeIsSizeIsTwo() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("B");
		bst.insert("A");
		bst.remove("A");
		assertEquals("B", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithOneRightNode() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(75);
		bst.remove(50);
		assertEquals("75", bst.toStringInorder());
	}

	@Test
	public void testRootWithNoChildren() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		assertEquals("M", bst.toStringInorder());
		bst.remove("M");
		assertEquals("", bst.toStringInorder());
	}

	@Test
	public void testRootWithNoRightChild() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("M");
		bst.insert("G");
		assertEquals("G M", bst.toStringInorder());
		bst.remove("M");
		assertEquals("G", bst.toStringInorder());
	}

	@Test
	public void testRemoveNoLeftChildAgain() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(75);
		bst.insert(85);
		bst.insert(90);
		assertEquals("50 75 85 90", bst.toStringInorder());
		bst.remove(85);
		assertEquals("50 75 90", bst.toStringInorder());
	}

	@Test
	public void testRemoveNoLeftChildAndAgain() {
		OrderedSet<Integer> bst = new OrderedSet<Integer>();
		bst.insert(50);
		bst.insert(25);
		bst.insert(75);
		bst.insert(85);
		bst.insert(80);
		bst.insert(90);
		assertEquals("25 50 75 80 85 90", bst.toStringInorder());
		bst.remove(85);
		assertEquals(5, bst.size());
		assertEquals("25 50 75 80 90", bst.toStringInorder());
	}

	@Test
	public void testRemoveNoRightChildFourthTry() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("C");
		bst.insert("D");
		bst.insert("B");
		bst.insert("E");
		bst.insert("A");
		assertEquals("A B C D E", bst.toStringInorder());
		bst.remove("A");
		assertEquals(4, bst.size());
		assertEquals("B C D E", bst.toStringInorder());
	}

	@Test
	public void testRemoveWhenRootChangesNoLeftChild() {
		OrderedSet<String> strings = new OrderedSet<String>();
		strings.insert("G");
		strings.insert("R");
		strings.insert("M");
		strings.insert("X");
		assertEquals("G M R X", strings.toStringInorder());
		strings.remove("G");
		assertEquals(3, strings.size());
		assertEquals("M R X", strings.toStringInorder());
	}

	@Test
	public void testRemoveFurtherDownNoLeftChild() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("R");
		bst.insert("T");
		bst.insert("S");
		bst.insert("U");
		assertEquals("G R S T U", bst.toStringInorder());
		assertTrue(bst.remove("R"));
		assertEquals("G S T U", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithMoreToTheRight() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("R");
		bst.insert("T");
		bst.insert("S");
		bst.insert("U");
		assertEquals("G R S T U", bst.toStringInorder());
		assertTrue(bst.remove("G"));
		assertEquals("R S T U", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithLeftTree() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("A");
		bst.insert("D");
		bst.insert("C");
		assertEquals("A C D G", bst.toStringInorder());
		assertTrue(bst.remove("G"));
		assertEquals("A C D", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithleftTreeAgain() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("J");
		bst.insert("X");
		bst.insert("A");
		bst.insert("F");
		bst.insert("E");
		assertTrue(bst.remove("G"));
		assertEquals("A E F J X", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithleftTree() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("J");
		bst.insert("A");
		assertEquals("A G J", bst.toStringInorder());
		assertTrue(bst.remove("G"));
		assertEquals("A J", bst.toStringInorder());
	}

	@Test
	public void testRemoveRootWithLeftTreeAndRightTree() {
		OrderedSet<String> bst = new OrderedSet<String>();

		bst.insert("G");
		bst.insert("J");
		bst.insert("D");
		bst.insert("B");
		bst.insert("C");
		assertEquals("B C D G J", bst.toStringInorder());
		assertTrue(bst.remove("G"));
		assertEquals("B C D J", bst.toStringInorder());
	}

	@Test
	public void testRemoveWithLeftTree() {
		OrderedSet<String> bst = new OrderedSet<String>();
		bst.insert("G");
		bst.insert("R");
		bst.insert("K");
		bst.insert("J");
		bst.insert("M");
		bst.insert("L");
		bst.insert("N");
		bst.insert("T");
		bst.insert("S");
		bst.insert("W");
		assertTrue(bst.remove("R"));
		assertEquals("G J K L M N S T W", bst.toStringInorder());
	}

	@Test
	public void testRemoveNodeWithLeftTreeAgain() {
		OrderedSet<String> bst = new OrderedSet<String>();

		bst.insert("G");
		bst.insert("B");
		bst.insert("R");
		bst.insert("K");
		bst.insert("J");
		bst.insert("M");
		bst.insert("L");
		bst.insert("N");
		bst.insert("T");
		bst.insert("S");
		bst.insert("W");

		assertTrue(bst.remove("M"));
		assertEquals("B G J K L N R S T W", bst.toStringInorder());
	}

	@Test
	public void testRemoveNodeWithLeftTree_18() {
		OrderedSet<String> bst = new OrderedSet<String>();

		bst.insert("G");
		bst.insert("B");
		bst.insert("R");
		bst.insert("K");
		bst.insert("J");
		bst.insert("M");
		bst.insert("L");
		bst.insert("N");
		bst.insert("T");
		bst.insert("S");
		bst.insert("W");

		assertTrue(bst.remove("K"));
		assertEquals("B G J L M N R S T W", bst.toStringInorder());
	}
}
