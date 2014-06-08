import java.awt.image.BufferedImage;

public class Player extends Character {
    private Item weapon, shield, head, torso, back, hands, legs, feet, accessory, consumable, misc;

	
    public Player() {super();}
    public Player(String imageLocation, int x, int y) {super(imageLocation, x, y);}
	
    public final Item getWeapon() {return weapon;}
    public final Item getShield() {return shield;}
    public final Item getHead() {return head;}
    public final Item getTorso() {return torso;}
    public final Item getBack() {return back;}
    public final Item getHands() {return hands;}
    public final Item getLegs() {return legs;}
    public final Item getFeet() {return feet;}
    public final Item getAccessory() {return accessory;}
    public final Item getConsumable() {return consumable;}
    public final Item getMisc() {return misc;}
	
    public final void setWeapon(Item weapon) {this.weapon = weapon;}
    public final void setShield(Item shield) {this.shield = shield;}
    public final void setHead(Item head) {this.head = head;}
    public final void setTorso(Item torso) {this.torso = torso;}
    public final void setBack(Item back) {this.back = back;}
    public final void setHands(Item hands) {this.hands = hands;}
    public final void setLegs(Item legs) {this.legs = legs;}
    public final void setFeet(Item feet) {this.feet = feet;}
    public final void setAccessory(Item accessory) {this.accessory = accessory;}
    public final void setConsumable(Item consumable) {this.consumable = consumable;}
    public final void setMisc(Item misc) {this.misc = misc;}

 
    
}
