package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    /*
	@Test
	public void testUpdateEndOfDay() {
		fail("Test not implemented");
	}
	*/
	
	@Test
	public void AgedBrieAged() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(1, itemBrie.getSellIn());
	}
	
	@Test
	public void RegularItemAged() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(1, itemBrie.getSellIn());
	}
	
	@Test
	public void BackstageAged() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(1, itemBrie.getSellIn());
	}
	
	@Test
	public void SulfurasDidNotAge() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(2, itemBrie.getSellIn());
	}
	
	@Test
	public void SulfurasDidNotAgeBelow0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", -1, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(-1, itemBrie.getSellIn());
	}
	
	@Test
	public void BrieAgedBelow0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(-1, itemBrie.getSellIn());
	}
	
	@Test
	public void BackstageAgedBelow0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(-1, itemBrie.getSellIn());
	}
	
	@Test
	public void RegularItemAgedBelow0() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 0, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(-1, itemBrie.getSellIn());
	}
	
	@Test
	public void qualityDoesNotDecreaseBelow0() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 2, 0) );

		store.updateEndOfDay();
				
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}
	
	@Test
	public void qualityDoesNotDecreaseBelow0AfterExpiry() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", -1, 0) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}
	
	@Test
	public void qualityCannotBeSetBelow0() {
		//Detected a bug as the quality of an item can be set below 0
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 2, -1) );
				
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}
	
	@Test
	public void qualityCannotBeSetAbove50() {
		//Detected a bug as the quality of an item can be set above 50
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 2, 51) );
				
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
	
	@Test
	public void qualityCannotRiseAbove50() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 50) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
	
	@Test
	public void RegularItemDecreasesBeforeExpiry() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 2, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(9, itemBrie.getQuality());
	}
	
	@Test
	public void RegularItemDecreasesAfterExpiry() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Conjured Mana Cake", 0, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(8, itemBrie.getQuality());
	}
	
	@Test
	public void BackstageBefore10DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage10DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(12, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage6DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(12, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage5DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage4DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage1DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality());
	}
	
	@Test
	public void Backstage0DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}
	
	@Test
	public void BackstagAfterConcert() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}
	
	@Test
	public void Sulfuras0DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}
	
	@Test
	public void Sulfuras10DaysLeft() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 10, 80) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}
	
	@Test
	public void SulfurasSetIncorrectlyTo50() {
		//Caught a bug
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 10, 50) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}
	
	@Test
	public void SulfurasSetIncorrectlyTo100() {
		// Caught a bug
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 10, 100) );
		
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}
}
