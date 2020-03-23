/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

public class GameMode {
	String name;
	int health, strength, defense, speed, luck; //Main Statistic
	int protection, block; //Player State
	int shock, burn, frozen, poisoned, doom; //Status Ailments
	//Extra Variables
	int dot, pt1, pt2, pt3, pop, healadj, jokeh; 
	int armor, glove, shoes;
	
	//Commands
	public void attackCom (GameMode Opponent)
	{
		//Attack calculator
		Random rng = new Random();
		int dmg = rng.nextInt(55) + 15;
		dmg = (int)(1 + (this.strength * 0.1)) * dmg;
		//Defense calculator
		int guard = Opponent.getProtection();
		int counter = 0;
		if (Opponent.getBlock() == 1) //Checks Opponent's Block State
		{
			System.out.println(Opponent.getName() + " blocks " + this.getName() + "'s attack!");
			dmg = 0;
			guard = 0;
			//counterattack!!
			counter = 1;
		}
		else if (guard > dmg)
		{
			System.out.println(this.getName() + " attacks " + Opponent.getName() + " but...");
			dmg = 0;
			guard = 0;
		}
		else if (dmg > 100)
		{
			System.out.println(this.getName() + " pummels " + Opponent.getName() + " relentlessly!");
			dmg = (int) dmg / 2;
			guard = (int) guard / 2;
		}
		else if (dmg > 70)
		{
			System.out.println(this.getName() + " pummels " + Opponent.getName() + " relentlessly!");
		}
		else  if (dmg > 60)
		{
			System.out.println(this.getName() + " strikes " + Opponent.getName() + " with a strong punch!");
		}
		else if (dmg > 45)
		{
			System.out.println(this.getName() + " attacks " + Opponent.getName() + " with a roundhouse kick!");
		}
		else  if (dmg > 25)
		{
			System.out.println(this.getName() + " smacks " + Opponent.getName() + ".");
		}
		else  if (dmg > 10)
		{
			System.out.println(this.getName() + " slaps " + Opponent.getName() + ".");
		}
		System.out.println("It does " + (dmg - guard) + " damage!");
		Opponent.setHealth(Opponent.getHealth() - (dmg - guard));
		Opponent.setProtection(0);
		Opponent.setBlock(0);
		if (counter == 1)
		{
			System.out.println(Opponent.getName() + " counter attacks!!");
			System.out.println(this.getName() + " is caught off guard and suffers a massive blow!");
			System.out.println("It does 99 damage!");
			this.health = this.health - 99;
		}
		
	}
	
	public void defendCom()
	{
		Random rng = new Random();
		//Defend Calculator
		int pro = rng.nextInt(25) + 5;
		pro = (int)(1 + (this.defense * 0.2)) * pro;
		if (pro > 40)
		{
			pro = 40 + this.defense;
		}
		this.protection = pro + this.defense;
		System.out.println(this.getName() + " has their guard up.");
		
		//Heal
		int chance = rng.nextInt(10) + 1;
		if (chance >= 9)
		{
			int heal = this.defense * 5;
			System.out.println(this.getName() + " recovers " + heal + " health.");
			this.health +=  heal;
		}
		
		//Block
		int shield = rng.nextInt(100) + this.defense;
		if (shield >= 95)
		{
			this.block = 1;

		}
	}
	
	public void magicCom(GameMode Opponent, GameMode Body)
	{
		//Magic Calculator
		Random rng = new Random();
		int spell = rng.nextInt(100) + 1;
		if (spell == 100)
		{
			System.out.println(this.getName() + " casts Doom!");
			if (this.doom > 0)
			{
				System.out.println(this.getName() + " lifts the curse!");
				System.out.println(Opponent.getName() + " receives a tremendous boost in vigor!");
				this.strength = this.strength + 2;
				this.defense = this.defense + 2;
				this.speed = this.speed + 2;
				this.health = this.health + 100;
			}
			else if (Opponent.getDoom() == 0)
			{
				System.out.println(Opponent.getName() + " is cursed to lose all their health within 6 turns.");
				Opponent.setDoom(6);
			}
			else if (Opponent.getDoom() > 0)
			{
				System.out.println(this.getName() + " has been given the ultimate punishment.");
				System.out.println(this.getName() + " is sent to his doom! They lose all their health!");
				this.health = this.health - this.health;
			}
			//Doom
		}
		else if (spell >= 97)
		{
			int bang = rng.nextInt(3) + 1;
			System.out.println(this.getName() + " casts Boom!");
			//Explosion
			if (bang == 3)
			{
				System.out.println(Opponent.getName() + " is caught in a huge explosion!");
				System.out.println("It does 99 damage!");
				Opponent.setHealth(Opponent.getHealth() - 99);

			}
			else if (bang == 2)
			{
				System.out.println("The spell backfires... " + this.getName() + " is caught in a huge explosion!");
				System.out.println("It does 99 damage!");
				this.health = this.health - 99;
			}
			else if (bang == 1)
			{
				System.out.println(Opponent.getName() + " is caught in a huge explosion!");
				System.out.println("... along with " + this.getName() + ".");
				System.out.println("It does 99 damage to both Players!");
				Opponent.setHealth(Opponent.getHealth() - 99);
				this.health = this.health - 99;
			}
			//Boom
		}
		else if (spell >= 94)
		{
			System.out.println(this.getName() + " casts Joker Hi-jinx.");
			System.out.println("It looks like...");
			int joke;
			joke = (int) spell / jokeh;
			int joker = rng.nextInt(6);
			if (joker == 6)
			{
				System.out.println ("... someone's got good luck!");
				Opponent.setHealth(Opponent.getHealth() + joke);
				Opponent.setStrength(Opponent.getStrength() + 3);
				Opponent.setDefense(Opponent.getDefense() + 3);
				Opponent.setSpeed(Opponent.getSpeed() + 3);
				Opponent.setLuck(Opponent.getLuck() + 1);
			}
			else if (joker == 5)
			{
				System.out.println ("... someone's got good luck!");
				this.health = this.health + joke;
				this.strength = this.strength + 3;
				this.defense = this.defense + 3;
				this.speed = this.speed + 3;
				this.luck = this.luck + 1;
				pt1 = this.strength;
				pt2 = this.defense;
				pt3 = this.speed;
			}
			else if (joker == 4)
			{
				System.out.println ("... everyone's got good luck!");
				this.health = this.health + joke;
				this.strength = this.strength + 3;
				this.defense = this.defense + 3;
				this.speed = this.speed + 3;
				this.luck = this.luck + 1;
				pt1 = this.strength;
				pt2 = this.defense;
				pt3 = this.speed;
				Opponent.setHealth(Opponent.getHealth() + joke);
				Opponent.setStrength(Opponent.getStrength() + 3);
				Opponent.setDefense(Opponent.getDefense() + 3);
				Opponent.setSpeed(Opponent.getSpeed() + 3);
				Opponent.setLuck(Opponent.getLuck() + 1);
			}
			else if (joker == 3)
			{
				System.out.println ("... someone's got bad luck!");
				Opponent.setHealth(Opponent.getHealth() - joke);
				Opponent.setBurn(3);
				Opponent.setFrozen(3);
				Opponent.setShock(3);
				Opponent.setPoison(3);
			}
			else if (joker == 2)
			{
				System.out.println ("... someone's got bad luck!");
				this.health = this.health - joke;
				this.burn = 3;
				this.frozen = 3;
				this.shock = 3;
				this.poisoned = 3;
			}
			else if (joker == 1)
			{
				System.out.println ("... everyone's got bad luck!");
				this.health = this.health - joke;
				this.burn = 3;
				this.frozen = 3;
				this.shock = 3;
				this.poisoned = 3;
				Opponent.setHealth(Opponent.getHealth() - joke);
				Opponent.setBurn(3);
				Opponent.setFrozen(3);
				Opponent.setShock(3);
				Opponent.setPoison(3);
			
			}
			else 
			{
				System.out.println("... nothing happens.");
			}
			
			//Joker Hi-jinx
		}
		else if (spell >= 91)
		{
			System.out.println(this.getName() + " casts Swap!");
			System.out.println(this.getName() + " and " + Opponent.getName() + " swap bodies!");
			//Holds Player current values
			Body.setHealth(this.health);
			Body.setStrength(this.strength);
			Body.setDefense(this.defense);
			Body.setSpeed(this.speed);
			Body.setLuck(this.luck);
			Body.setBurn(this.burn);
			Body.setFrozen(this.frozen);
			Body.setShock(this.shock);
			Body.setPoison(this.poisoned);
			Body.setDoom(this.doom);
			//Set Player's opponent values
			this.health = Opponent.getHealth();
			this.strength = Opponent.getStrength();
			this.defense = Opponent.getDefense();
			this.speed = Opponent.getSpeed();
			this.luck = Opponent.getLuck();
			this.burn = Opponent.getBurn();
			this.frozen = Opponent.getFrozen();
			this.shock = Opponent.getShock1();
			this.poisoned = Opponent.getPoison();
			this.doom = Opponent.getDoom();
			//Set Opponent's player values
			Opponent.setHealth(Body.getHealth());
			Opponent.setStrength(Body.getStrength());
			Opponent.setDefense(Body.getDefense());
			Opponent.setSpeed(Body.getSpeed());
			Opponent.setLuck(Body.getLuck());
			Opponent.setBurn(Body.getBurn());
			Opponent.setFrozen(Body.getFrozen());
			Opponent.setShock(Body.getShock());
			Opponent.setPoison(Body.getPoison());
			Opponent.setDoom(Body.getDoom());
			//Swap
		}
		else if (spell >= 86)
		{
			System.out.println(this.getName() + " casts Poison!");
			if (Opponent.getPoison() > 0)
			{
				System.out.println("It is already in affect.");
			}
			else
			{
				System.out.println("It gradually starts taking effect over time!");
				int poison = rng.nextInt(10) + 1;
				Opponent.setPoison(poison);
			}
			//Poison
		}
		else if (spell >= 81)
		{
			System.out.println(this.getName() + " tries using magic. Nothing happens...");
			//Nothing
		}
		else if (spell >= 74)
		{
			System.out.println(this.getName() + " casts Drain!");
			int drain = (int) spell / 2;
			System.out.println(this.getName() + " steals " + drain + " points of " + Opponent.getName() + " health.");
			this.health = this.health + drain;
			Opponent.setHealth(Opponent.getHealth() - drain);
			//Drain
		}
		else if (spell >= 66)
		{
			int heal;
			heal = (int) spell / healadj;
			System.out.println(this.getName() + " casts Heal!");
			System.out.println(this.getName() + " recovers " + heal + " health.");
			this.health = this.health + heal;
			if (healadj == 1)
			{
				healadj++;
			}
			else
			{
				//nothing
			}
			//Heal
		}
		else if (spell >= 56)
		{
			System.out.println(this.getName() + " casts Thunder!");
			System.out.println(Opponent.getName() + " is struck by lightning!");
			
			if (Opponent.getShock() > 0)
			{
				spell = spell + 50;
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
				Opponent.setShock(0);
				System.out.println(Opponent.getName() + " breaks out of stun!");
			}
			else
			{
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
				int zap = rng.nextInt(3);
				Opponent.setShock(zap);
				System.out.println(Opponent.getName() + " may temporarily be unable to act!");
			}
			//Thunder
		}
		else if (spell >= 36)
		{
			System.out.println(this.getName() + " casts Blizzard!");
			System.out.println(Opponent.getName() + " is caught in a storm of snow and ice!");
			if (Opponent.getFrozen() > 0)
			{
				spell = spell + 25;
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
			}
			else
			{
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
				int freeze = rng.nextInt(4);
				Opponent.setFrozen(freeze);
			}
			if (Opponent.getBurn() > 0)
			{
				Opponent.setBurn(0);
				System.out.println(Opponent.getName() + " is no longer on fire.");

			}
			//Blizzard
		}
		else if (spell >= 11)
		{
			System.out.println(this.getName() + " casts Fire!");
			System.out.println(Opponent.getName() + " is set ablaze!");
			if (Opponent.getBurn() > 0)
			{
				spell = spell + 20;
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
			}
			else
			{
				System.out.println("It does " + spell + " damage!");
				Opponent.setHealth(Opponent.getHealth() - spell);
				int burn = rng.nextInt(5) + 1;
				Opponent.setBurn(burn);
			}
			if (Opponent.getFrozen() > 0)
			{
				Opponent.setFrozen(0);
				System.out.println(Opponent.getName() + " is warmed up.");

			}
			//Fire
		}
		else if (spell <= 10)
		{
			if (pop > 1)
			{
				pop = pop * 2;
				System.out.println(this.getName() + " casts Magic Bubble!");
				System.out.println("It's size is now double!");
				System.out.println(Opponent.getName() + " get blasted by a large pop");
				System.out.println("It does " + pop + " damage!");
			}
			else
			{
				spell = 10;
				System.out.println(this.getName() + " casts Magic Bubble!");
				System.out.println("It does " + spell + " damage!");
				pop = spell;
			}
			//Magic Bubble
		}
	}
	
	public void itemCom(GameMode Opponent) {
		//Item Calculator
		Random rng = new Random();
		int item = rng.nextInt(100) + 1;
		if (item == 100)
		{
			if (block == 1)
			{
				System.out.println(this.name + " throws a Hatchet at " + Opponent.getName() + ".");
				System.out.println("But " + Opponent.getName() + " blocks it!");
			}
			else
			{
				System.out.println(this.name + " throws a Hatchet at " + Opponent.getName() + ".");
				System.out.println("It does 125 damage.");
				Opponent.setHealth(Opponent.getHealth() - 125);
			}
		}
		else if (item >= 95)
		{
			
			//First aid kit
			System.out.println(this.name + " uses a First aid kit.");
			System.out.println("They recover 100 health points!");
			this.health = this.health + 100;
		}
		else if (item >= 91)
		{
			//Medicine
			System.out.println(this.name + " uses Medicine.");
			System.out.println("They're cured of all status ailments!");
			this.burn = 0;
			this.frozen = 0;
			this.shock = 0;
			this.poisoned = 0;
			this.doom = 0;
		}
		else if (item >= 81)
		{
			//Soda 
			System.out.println(this.name + " drinks Soda.");
			System.out.println("They receive certain effects/side-effects: ");
			System.out.println("-20 Health points, +2 Speed, -1 Strength.");
			if (this.health <= 20)
			{
				this.health = 1;
			}
			this.speed = this.speed + 1;
			this.strength = this.strength - 1;
			if (this.strength < 0)
			{
				this.strength = 0;
			}
		}
		else if (item >= 71)
		{
			//Tea
			System.out.println(this.name + " drinks Tea.");
			System.out.println("They receive certain effects/side-effects: ");
			System.out.println("+35 Health points, +1 Strength, Cures Poison.");
			this.health = this.health + 35;
			this.strength = this.strength + 1;
			this.poisoned = 0;
		}
		else if (item >= 61)
		{
			//Coffee
			System.out.println(this.name + " drinks Coffee.");
			System.out.println("They receive certain effects/side-effects: ");
			System.out.println("+10 Health points, +1 Speed, Cures Freeze.");
			this.health = this.health + 10;
			this.speed = this.speed + 1;
			this.frozen = 0;
		}
		else if (item >= 46)
		{
			//Water
			if (this.burn > 0)
			{
				System.out.println(this.name + " uses Water to douse the fire.");
				this.burn = 0;
			}
			else
			{
				System.out.println(this.name + " drinks Water.");
				System.out.println("They're refreshed and hydrated.");
			}
		}
		else if (item >= 41)
		{
			System.out.println(this.name + " throws a bomb at " + Opponent.getName() + ".");
			System.out.println(Opponent.getName() + " is caught in a huge explosion!");
			System.out.println("It does 99 damage!");
			Opponent.setHealth(Opponent.getHealth() - 99);
		}
		else if (item >= 36)
		{
			if (block == 1)
			{
				System.out.println(this.name + " throws Hot Coffee at " + Opponent.getName() + ".");
				System.out.println("But " + Opponent.getName() + " blocks it!");
			}
			else
			{
				System.out.println(this.name + " throws Hot Coffee at " + Opponent.getName() + ".");
				System.out.println("It does 15 damage.");
				Opponent.setHealth(Opponent.getHealth() - 15);
				Opponent.setBurn(2);
			}
		}
		else if (item >= 31)
		{
			//Medicine 2
			int rx = 0;
			int ry = 0;
			int side = 0;
			System.out.println(this.name + " uses Medicine .");
			System.out.println("They receive certain effects/side-effects: ");
			System.out.println("Cures all status ailments, but lose health");
			if (this.burn != 0)
			{
				rx++;
			}
			if (this.frozen != 0)
			{
				rx++;
			}
			if (this.shock != 0)
			{
				rx++;
			}
			if (this.poisoned != 0)
			{
				rx++;
			}
			if (this.doom != 0)
			{
				ry = 50;
			}
			side = (rx * 10) + ry;
			System.out.println("It does " + side + " damage.");
			this.health = this.health - side;
		}
		else if (item <= 30)
		{
			//Equip: 100 - 91
			if ((item == 30) && (armor == 1))
			{
				System.out.println(this.name + " equips heavy armor.");
				System.out.println("Their defense increases by 2!");
				this.defense = this.defense + 2;
				armor = armor - 1;
			}
			else if (item >= 25)
			{
				System.out.println(this.name + " equips a shield.");
				this.block = 1;
			}
			else if ((item >= 15) && (shoes == 1))
			{
				System.out.println(this.name + " puts on speed shoes.");
				System.out.println("Their speed increases by 2!");
				this.speed = this.speed + 2;
				shoes = shoes - 1;
			}
			else if ((item < 15) && (glove == 1))
			{
				System.out.println(this.name + " puts on combat gloves.");
				System.out.println("Their strength increases by 2!");
				this.strength = this.strength + 2;
				glove = glove - 1;
			}
		}
		
	}
	
	//Special Actions
	public void AttackBoost() 
	{
		Random rng = new Random();
		int boost = rng.nextInt(100) + (1);
		if (boost >= 90)
		{
			System.out.println(this.getName() + " strength increases by 1!!");
			this.strength = this.strength + 1;
			pt1 = this.strength;
		}
	}
	
	public void CritHit(GameMode Opponent) 
	{
		Random rng = new Random();
		int crit = rng.nextInt(100) + (this.luck * this.speed);
		if (crit >= 91)
		{
			System.out.println(this.getName() + " goes in for another strike!");
			this.attackCom(Opponent);
			this.luck = this.luck - 1;
		}
	}
	
	public int DodgeAttack (GameMode Opponent)
	{
		Random rng = new Random();
		int evasion = 0;
		int evade = rng.nextInt(50) + (this.speed);
		if (evade >= 46)
		{
			System.out.println(Opponent.getName() + " attacks " + this.getName() + " but they dodge it!");
			evasion = 1;
		}
		return evasion;
	}
	
	//Setters
	public void setName (String name){
		this.name = name;
		}
	
	public void setStats  (int health, int strength, int defense, int speed)
	{
		//primary stats
		this.health = 500 + (health * 60);
		this.strength = strength;
		this.defense = defense;
		this.speed = speed;
		this.luck = 5;
		//secondary
		this.protection = 0;
		this.block = 0;
		this.dot = 0;
		//status
		this.burn = 0;
		this.frozen = 0;
		this.shock = 0;
		this.poisoned = 0;
		this.doom = 0;
		pt1 = this.strength;
		pt2 = this.defense;
		pt3 = this.speed;
		pop = 0;
		healadj = 1;
		jokeh = 1;
		armor = 1;
		shoes = 1;
		glove = 1;
	}
	
	public void setDefense (int defense){
		this.defense = defense;
		pt2 = this.defense;

		}
	
	public void setHealth (int health){
		this.health = health;
		}
	
	public void setStrength (int strength){
		this.strength = strength;
		pt1 = this.strength;
		}
	
	public void setSpeed (int speed) {
		this.speed = speed;
		pt3 = this.speed;
	}
	
	public void setLuck (int luck) {
		this.luck = luck;
	}
	
	public void setProtection (int protection) {
		this.protection = protection;
	}
	
	public void setBlock (int block) {
		this.block = block;
	}
	
	//Setters - Status Ailments
	public void setBurn (int burn) {
		this.burn = burn;
	}
	
	public void setFrozen (int freeze) {
		this.frozen = freeze;
	}
	
	public void setShock (int shock) {
		this.shock = shock;
	}
	
	
	public void setPoison (int poison) {
		this.poisoned = poison;
	}
	
	public void setDoom (int doom) {
		this.doom = doom;
	}
	
	//Getters - Status Ailments
	public int getBurn() {
		return burn;
	}
	
	public int getFrozen() {
		return frozen;
	}
	
	
	public int getShock1() {
		return shock;
	}
	
	public int getPoison() {
		return poisoned;
	}
	
	public int getDoom() {
		return doom;
	}
	
	//Getters - Main Statistics
	public String getName (){
		return name;
		}
	
	public int getHealth (){
		return health;
		}
	
	public int getStrength (){
		return strength;
		}
	
	public int getDefense (){
		return defense;
		}
	
	public int getSpeed (){
		return speed;
		}
	
	public int getLuck (){
		return luck;
		}
	
	//Getters - Player States
	public int getProtection (){
		return protection;
		}
	
	public int getBlock (){
		return block;
		}
	
	
	//Getters - Status Ailments
	public void getStatus () {
		if (this.burn > 0)
		{
			System.out.println(this.getName() + " is still burning!");
			System.out.println("This does 10 damage.");
			this.health = this.health - 10;
			this.burn = this.burn - 1;
			if (this.burn == 0)
			{
				System.out.println(this.getName() + " is no longer on fire.");
				
			}
		}
		if (this.frozen > 0)
		{
			this.frozen = this.frozen - 1;
			System.out.println(this.getName() + " is shivering from the cold!");
			System.out.println("Their performance temporarily decreases.");
			this.strength = this.strength - 1;
			if (this.strength < 0)
			{
				this.strength = 0;
			}
			this.defense = this.defense - 1;
			if (this.defense < 0)
			{
				this.defense = 0;
			}
			this.speed = this.speed - 1;
			if (this.speed < 0)
			{
				this.speed = 0;
			}
			
			if (this.frozen == 0)
			{
				System.out.println(this.getName() + " is warmed up.");
				this.strength = pt1;
				this.defense = pt2;
				this.speed = pt3;
			}
		
		}
		if (this.shock > 0)
		{
			System.out.println(this.getName() + " may still be shocked!");
			System.out.println("They may be unable to act!");
		}
		if (this.poisoned > 0)
		{
			int poisondmg;
			this.dot = this.dot + 3;
			poisondmg = 13 + this.dot;
			System.out.println(this.getName() + " is poisoned!");
			System.out.println("This does " + poisondmg + " damage.");
			this.health = this.health - poisondmg;
			this.poisoned = this.poisoned - 1;
			if (this.poisoned == 0)
			{
				System.out.println(this.getName() + " is no longer poisoned.");
			}
		}
		if (this.doom > 0)
		{
			this.doom = this.doom - 1;
			if (this.doom == 0)
			{
				System.out.println(this.name + " has met his doom!");
				this.health = this.health - this.health;
			}
			else
			{
				System.out.println(this.name + " has " + this.doom + " turns left to live.");
			}
		}
		System.out.println(this.getName() + " HP = " + this.getHealth());
		
	}
	
	public int getShock() {
		if (this.shock > 0)
		{
			this.shock = this.shock - 1;
			if (this.shock > 0)
			{
				System.out.println(this.getName() + " is still stunned by the electric shock!");

			}
			else if (this.shock == 0)
			{
				System.out.println(this.getName() + " breaks out of stun.");
			}
			
		}
		return shock;
	}
	
	
	//Other
	public boolean winStatus() {
		if (health <= 0)
			return true;
		else
			return false;
		}
	

}
