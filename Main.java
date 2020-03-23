/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[])
	{
		String ch, ch1, ch2, ch3;
		int hp, str, df, spd, p1, p2;
		int total = 0;
		int turn = 0;
		int win = 0;
		int m1 = 1;
		int m2 = 1;
		int i1 = 5;
		int i2 = 5;
		int [][] P1 = new int [50][10]; 
		int [][] P2 = new int [50][10];
		System.out.println("Would you like to play a game? (Enter Yes(Y) or No(N))");
		Scanner console = new Scanner (System.in);
		ch = console.next();
		while (((ch.equals("Y")) || (ch.equals("y"))) && ((!ch.equals("N")) || (!ch.equals("n"))))
		{
			turn = 0;
			win = 0;
			GameMode Dummy = new GameMode();
			GameMode Player1 = new GameMode();
			Player1.setName("Player 1");
			System.out.println("You are " + Player1.getName() + ".");
			System.out.println("Customize your stats.");
			ch1 = ch;
			while (((ch1.equals("Y")) || (ch1.equals("y"))) && ((!ch1.equals("N")) || (!ch1.equals("n"))))
			{
				System.out.println("You have 6 points to allocate to either Health (+75 per point)");
				System.out.println("Strength, Defense, or Speed. Please enter the amount you would like for...");
				System.out.println("Health: ");
				hp =  console.nextInt();
				System.out.println("Strength: ");
				str =  console.nextInt();
				System.out.println("Defense: ");
				df =  console.nextInt();
				System.out.println("Speed: ");
				spd =  console.nextInt();
				total = hp + str + df + spd;
				if (total == 6) {
					Player1.setStats(hp,str, df, spd);
					System.out.println("Your current stats: ");
					System.out.println("Health: " + Player1.getHealth());
					System.out.println("Strength: " + Player1.getStrength());
					System.out.println("Defense: " + Player1.getDefense());
					System.out.println("Speed: " + Player1.getSpeed());
					System.out.println("Would you like to change your stats?  (Enter Yes(Y) or No(N))");
					ch1 = console.next();
				}
				else
				{
					System.out.println("The values you entered are invalid. Try again");
				}
			}
				GameMode Player2 = new GameMode();
				Player2.setName("Player 2");
				System.out.println("You are " + Player2.getName() + ".");
				System.out.println("Customize your stats.");
				ch1 = ch;
				while (((ch1.equals("Y")) || (ch1.equals("y"))) && ((!ch1.equals("N")) || (!ch1.equals("n"))))
				{
					System.out.println("You have 6 points to allocate to either Health (+75 per point)");
					System.out.println("Strength, Defense, or Speed. Please enter the amount you would like for...");
					System.out.println("Health: ");
					hp =  console.nextInt();
					System.out.println("Strength: ");
					str =  console.nextInt();
					System.out.println("Defense: ");
					df =  console.nextInt();
					System.out.println("Speed: ");
					spd =  console.nextInt();
					total = hp + str + df + spd;
					if (total == 6) {
						Player2.setStats(hp,str, df, spd);
						System.out.println("Your current stats: ");
						System.out.println("Health: " + Player2.getHealth());
						System.out.println("Strength: " + Player2.getStrength());
						System.out.println("Defense: " + Player2.getDefense());
						System.out.println("Speed: " + Player2.getSpeed());
						System.out.println("Would you like to change your stats?  (Enter Yes(Y) or No(N))");
						ch1 = console.next();
					}
					else
					{
						System.out.println("The values you entered are invalid. Try again");
					}
				}
				updateData(turn, P1, P2, Player1, Player2);
				System.out.println("Are you ready? (Enter Yes(Y) or No(N))");
				ch2 = console.next();
				win = 0;
				i1 = 5;
				i2 = 5;
				while (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
				{
					if (win == 1)
					{
						break;
					}
					turn++;
					updateData(turn, P1, P2, Player1, Player2);
					System.out.println("============================================");
					System.out.println("||\t\t  Turn " + turn + "\t\t  ||");
					System.out.println("||              ----------                ||");
					System.out.println("||\t     " + Player1.getName() + " HP = " + Player1.getHealth() + "\t\t  ||");
					System.out.println("||\t     " + Player2.getName() + " HP = " + Player2.getHealth() + "\t\t  ||");
					System.out.println("============================================");
					p1 = turn;
					p2 = turn;
					while (p1 == p2)
					{
						System.out.println(Player1.getName() + " Turn!");
						Player1.getStatus();
						if ((Player1.winStatus() ==  true) && (Player2.winStatus() ==  true) && (win != 1))
						{
							updateData(turn, P1, P2, Player1, Player2);
							System.out.println("There are no winner this match! It's a Draw!");
							//Results
							showResults(P1, P2, turn);
							System.out.println("Would you like to play again?(Enter Yes(Y) or No(N))");
							ch2 = console.next();
							if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
							{
								win = 1;
								System.out.println("Prepare for a Rematch!");
								break;//edit: properly ask  for  a rematch
							}
							else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
							{
								System.out.println("Game Over!");
								System.exit(0);
							}
						}
						if ((Player2.winStatus() ==  true) && (win != 1))
						{
							updateData(turn, P1, P2, Player1, Player2);
							System.out.println("Player 2 has been defeated.");
							System.out.println("Player 1 is Victorious!.");
							//Results
							showResults(P1, P2, turn);
							System.out.println("Would you like to play another round?(Enter Yes(Y) or No(N))");
							ch2 = console.next();
							if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
							{
								System.out.println("Prepare for a Rematch!");
								win = 1;
								break;//edit: properly ask  for  a rematch
							}
							else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
							{
								System.out.println("Game Over!");
								System.exit(0);
							}
							
						}
						System.out.println("1.Attack     2.Magic(" + m1 + ")     3.Defend     4.Item(" + i1 + ")");
						ch3 = console.next();
						if ((ch3.equals("Attack")) || (ch3.equals("attack"))|| (ch3.equals("1")))
						{
							if ((Player2.DodgeAttack(Player1)) == 1)
							{
								p1++;
							}
							else if (Player1.getShock() > 0)
							{
								p1++;
							}
							else
							{
								Player1.AttackBoost();
								Player1.attackCom(Player2);
								Player1.CritHit(Player2);
								p1++;
							}
							m1 = 1;
						}
						else if ((ch3.equals("Defend")) || (ch3.equals("defend"))|| (ch3.equals("3")))
						{
							if (Player1.getShock() > 0)
							{
								p1++;
							}
							else
							{
								Player1.defendCom();
								p1++;
							}
							m1 = 1;
							
						}
						else if ((ch3.equals("Magic")) || (ch3.equals("magic"))|| (ch3.equals("2")))
						{
							if (m1 == 1)
							{
								if (Player1.getShock() > 0)
								{
									p1++;
									m1 = 1;
								}
								else
								{
									Player1.magicCom(Player2, Dummy);
									p1++;
									m1 = 0;
								}
								
							}
							else 
							{
								System.out.println("Can't use Magic. Needs to recharge.");
							}
							
						}
						else if ((ch3.equals("Item")) || (ch3.equals("item"))|| (ch3.equals("4")))
						{
							if (Player1.getShock() > 0)
							{
								p1++;
								m1 = 1;
							}
							else if (i1 != 0)
							{
								Player1.itemCom(Player2);
								i1--;
								p1++;
								m1 = 1;
							}
							else
							{
								System.out.println("Your out of Items!");
							}
						}
						else
						{
							System.out.println("Invalid input. Try again.");
						}
					}
					if ((Player1.winStatus() ==  true) && (Player2.winStatus() ==  true) && (win != 1))
					{
						updateData(p1, P1, P2, Player1, Player2);
						System.out.println("There are no winner this match! It's a Draw!");
						//Results
						showResults(P1, P2, turn);
						System.out.println("Would you like to play again?(Enter Yes(Y) or No(N))");
						ch2 = console.next();
						if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
						{
							win = 1;
							System.out.println("Prepare for a Rematch!");
							break;//edit: properly ask  for  a rematch
						}
						else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
						{
							System.out.println("Game Over!");
							System.exit(0);
						}
					}
					if ((Player2.winStatus() ==  true) && (win != 1))
					{
						updateData(p1, P1, P2, Player1, Player2);
						System.out.println("Player 2 has been defeated.");
						System.out.println("Player 1 is Victorious!.");
						//Results
						showResults(P1, P2, p1);
						System.out.println("Would you like to play another round?(Enter Yes(Y) or No(N))");
						ch2 = console.next();
						if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
						{
							win = 1;
							System.out.println("Prepare for a Rematch!");
							break;//edit: properly ask  for  a rematch
						}
						else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
						{
							System.out.println("Game Over!");
							System.exit(0);
						}
						
					}
					else
					{
						//nothing
					}
					while (p1 != p2)
					{
						System.out.println(Player2.getName() + " Turn!");
						Player2.getStatus();
						if ((Player1.winStatus() ==  true) && (Player2.winStatus() ==  true) && (win != 1))
						{
							updateData(p1, P1, P2, Player1, Player2);
							System.out.println("There are no winner this match! It's a Draw!");
							//Results
							showResults(P1, P2, turn);
							System.out.println("Would you like to play again?(Enter Yes(Y) or No(N))");
							ch2 = console.next();
							if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
							{
								win = 1;
								System.out.println("Prepare for a Rematch!");
								break;//edit: properly ask  for  a rematch
							}
							else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
							{
								System.out.println("Game Over!");
								System.exit(0);
							}
							
						}
						if ((Player1.winStatus() ==  true) && (win != 1))
						{
							updateData(p1, P1, P2, Player1, Player2);
							System.out.println("Player 1 has been defeated.");
							System.out.println("Player 2 is Victorious!.");
							//Results
							showResults(P1, P2, turn);
							System.out.println("Would you like to play again?(Enter Yes(Y) or No(N))");
							ch2 = console.next();
							if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
							{
								win = 1;
								System.out.println("Prepare for a Rematch!");
								break;//edit: properly ask  for  a rematch
							}
							else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
							{
								System.out.println("Game Over!");
								System.exit(0);
							}
							
						}
						System.out.println("1.Attack     2.Magic(" + m2 + ")     3.Defend     4.Item(" + i2 + ")");
						ch3 = console.next();
						if ((ch3.equals("Attack")) || (ch3.equals("attack"))|| (ch3.equals("1")))
						{
							if ((Player1.DodgeAttack(Player2)) == 1)
							{
								p2++;
							}
							else if (Player2.getShock() > 0)
							{
								p2++;
							}
							else
							{
								Player2.AttackBoost();
								Player2.attackCom(Player1);
								Player2.CritHit(Player1);
								p2++;
							}
							m2 = 1;
						}
						else if ((ch3.equals("Defend")) || (ch3.equals("defend"))|| (ch3.equals("3")))
						{
							if (Player2.getShock() > 0)
							{
								p2++;
							}
							else
							{
								Player2.defendCom();
								p2++;
							}
							m2 = 1;
						}
						else if ((ch3.equals("Magic")) || (ch3.equals("magic"))|| (ch3.equals("2")))
						{
							if (m2 == 1)
							{
								if (Player2.getShock() > 0)
								{
									p2++;
									m2 = 1;
								}
								else
								{
									Player2.magicCom(Player1, Dummy);
									p2++;
									m2 = 0;
								}
							}
							else 
							{
								System.out.println("Can't use Magic. Needs to recharge.");
							}
							
						}
						else if ((ch3.equals("Item")) || (ch3.equals("item"))|| (ch3.equals("4")))
						{
							if (Player2.getShock() > 0)
							{
								p2++;
								m2 = 1;
							}
							else if (i2 != 0)
							{
								Player2.itemCom(Player1);
								i2--;
								p2++;
								m2 = 1;
							}
							else
							{
								System.out.println("Your out of Items!");
							}
							
						}
						else
						{
							System.out.println("Invalid input. Try again.");
						}
					}
					if ((Player1.winStatus() ==  true) && (Player2.winStatus() ==  true) && (win != 1))
					{
						updateData(p2, P1, P2, Player1, Player2);
						System.out.println("There are no winner this match! It's a Draw!");
						//Results
						showResults(P1, P2, turn);
						System.out.println("Would you like to play again?(Enter Yes(Y) or No(N))");
						ch2 = console.next();
						if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
						{
							win = 1;
							System.out.println("Prepare for a Rematch!");
							break;//edit: properly ask  for  a rematch
						}
						else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
						{
							System.out.println("Game Over!");
							System.exit(0);
						}
					}
					if ((Player1.winStatus() ==  true) && (win != 1))
					{
						updateData(p2, P1, P2, Player1, Player2);
						System.out.println("Player 1 has been defeated.");
						System.out.println("Player 2 is Victorious!.");
						//Results
						showResults(P1, P2, p2);
						System.out.println("Would you like to play again? (Enter Yes(Y) or No(N))");
						ch2 = console.next();
						if (((ch2.equals("Y")) || (ch2.equals("y"))) && ((!ch2.equals("N")) || (!ch2.equals("n"))))
						{
							win = 1;
							System.out.println("Prepare for a Rematch!");
							break;//edit: properly ask  for  a rematch
						}
						else if (((ch2.equals("N")) || (ch2.equals("n"))) && ((!ch2.equals("Y")) || (!ch2.equals("y"))))
						{
							System.out.println("Game Over!");
							System.exit(0);
						}
						
					}
					else
					{
						//nothing
					}
					
				}
			}
		}
	
	public static void showResults (int [][] One, int [][] Two, int num) {
		//Results
		num++;
		System.out.println("Results: ");
		System.out.println("=========================================================================================================================================================================");
		System.out.println("||      |Player1                                                                        |Player2                                                                       ||");
		System.out.println("||------|-------------------------------------------------------------------------------|------------------------------------------------------------------------------||");
		System.out.println("||Turn\t|Health\t|Str\t|Dfs\t|Spd\t|Lck\t|B\t|F\t|S\t|P\t|D\t|Health\t|Str\t|Dfs\t|Spd\t|Lck\t|B\t|F\t|S\t|P\t|D     ||");
		System.out.println("||------|-------------------------------------------------------------------------------|------------------------------------------------------------------------------||");
		for (int k = 1; k < num; k++)
		{
			if ((One[k][0] <= 0) || (Two[k][0] <= 0))
			{
				System.out.println("||End\t|" + One[k][0] + "\t|" + One[k][1] + "\t|" + One[k][2] + "\t|"
						 + One[k][3] + "\t|" + One[k][4] + "\t|" + One[k][5] + "\t|" + One[k][6] + "\t|" + One[k][7] + "\t|" 
						 + One[k][8] + "\t|" + One[k][9] + "\t|" + Two[k][0] + "\t|" + Two[k][1] + "\t|" + Two[k][2] + "\t|"
						 + Two[k][3] + "\t|" + Two[k][4] + "\t|" + Two[k][5] + "\t|" + Two[k][6] + "\t|" + Two[k][7] + "\t|" 
						 + Two[k][8] + "\t|" + Two[k][9]+ "     ||");
			}
			else
			{
				System.out.println("||" + k + "\t|" + One[k][0] + "\t|" + One[k][1] + "\t|" + One[k][2] + "\t|"
						 + One[k][3] + "\t|" + One[k][4] + "\t|" + One[k][5] + "\t|" + One[k][6] + "\t|" + One[k][7] + "\t|" 
						 + One[k][8] + "\t|" + One[k][9] + "\t|" + Two[k][0] + "\t|" + Two[k][1] + "\t|" + Two[k][2] + "\t|"
						 + Two[k][3] + "\t|" + Two[k][4] + "\t|" + Two[k][5] + "\t|" + Two[k][6] + "\t|" + Two[k][7] + "\t|" 
						 + Two[k][8] + "\t|" + Two[k][9]+ "     ||");
			}
		}
		System.out.println("=========================================================================================================================================================================");
	}
	
	public static void updateData (int num, int One[][], int Two[][], GameMode P1, GameMode P2) {
		//Player 1 Stats
		One[num][0] = P1.getHealth();
		One[num][1] = P1.getStrength();
		One[num][2] = P1.getDefense();
		One[num][3] = P1.getSpeed();
		One[num][4] = P1.getLuck();
		//Player 1 States
		One[num][5] = P1.getBurn();
		One[num][6] = P1.getFrozen();
		One[num][7] = P1.getShock1();
		One[num][8] = P1.getPoison();
		One[num][9] = P1.getDoom();
		
		//Player 2 Stats
		Two[num][0] = P2.getHealth();
		Two[num][1] = P2.getStrength();
		Two[num][2] = P2.getDefense();
		Two[num][3] = P2.getSpeed();
		Two[num][4] = P2.getLuck();
		//Player 2 States
		Two[num][5] = P2.getBurn();
		Two[num][6] = P2.getFrozen();
		Two[num][7] = P2.getShock1();
		Two[num][8] = P2.getPoison();
		Two[num][9] = P2.getDoom();
	}
	}

