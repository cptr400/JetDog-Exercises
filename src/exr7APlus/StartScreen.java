package exr7APlus;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import role.EntityModel;
import role.TextModel;

public class StartScreen {

	private List<EntityModel> startScreen;

	private int cost;
	private Exercise7A controller;
	private String message = "Enter Your Name: ";
	private TextModel prompt;
		
	public StartScreen(Exercise7A controller) {
		this.controller = controller;
	}

	public void addComponents() {
		startScreen = new ArrayList<>();
		EntityModel em = new TextModel(controller.getWidth() / 2, 5 * controller.getHeight() / 10) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}
			
			@Override
			protected void setAppearance() {
				setView("Space Aliens", new Font("Courier", Font.BOLD, 56), Color.GREEN);
			}

		};
		
		em.setWrappedMode(true);
		em.setGhost(true);
		startScreen.add(em);
		
		prompt = new TextModel(controller.getWidth() / 2, 6 * controller.getHeight() / 10) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView(message, Color.RED);
			}

		};
		startScreen.add(prompt);
		
		em = new AlienPinkSoldier(controller.getWidth()/2 - 50, controller.getHeight()/4 - 50);
		cost = ((AlienPinkSoldier)em).getCost();
		em.setActive(true);
		em.setGhost(true);
		startScreen.add(em);
		
		em = new TextModel(controller.getWidth()/2 + 50, controller.getHeight()/4 - 50) {

			final int cost = StartScreen.this.getCost();
			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				String str = String.format("%4d Points", cost);
				setView(str, Color.RED);
			}

		};
		startScreen.add(em);
		
		em = new AlienGreenSoldier(controller.getWidth()/2 - 50, controller.getHeight()/4);
		cost = ((AlienGreenSoldier)em).getCost();
		em.setActive(true);
		em.setGhost(true);
		startScreen.add(em);
		
		em = new TextModel(controller.getWidth()/2 + 50, controller.getHeight()/4) {
			final int cost = StartScreen.this.getCost();

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				String str = String.format("%4d Points", cost);
				setView(str, Color.RED);			
			}


		};
		em.setGhost(true);
		startScreen.add(em);
		
		em = new FireballB(controller.getWidth()/2 - 50, controller.getHeight()/4 + 50);		
		cost = ((FireballB)em).getCost();
		em.setYVelocity(0);
		em.setActive(true);
		em.setGhost(true);
		startScreen.add(em);
		
		em = new TextModel(controller.getWidth()/2 + 50, controller.getHeight()/4 + 50) {
			final int cost = StartScreen.this.getCost();

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				String str = String.format("%4d Point(s)", cost);
				setView(str, Color.RED);			
			}


		};
		em.setGhost(true);
		startScreen.add(em);

		em = new FireballA(controller.getWidth()/2 - 50, controller.getHeight()/4 + 100);
		cost = ((FireballA)em).getCost();
		em.setYVelocity(0);
		em.setActive(true);
		em.setGhost(true);
		startScreen.add(em);
		
		em = new TextModel(controller.getWidth()/2 + 50, controller.getHeight()/4 + 100) {
			final int cost = StartScreen.this.getCost();

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				String str = String.format("%4d Point(s)", cost);
				setView(str, Color.RED);			
			}


		};
		em.setGhost(true);
		startScreen.add(em);
	}

	private int getCost() {
		return cost;
	}
	
	public EntityModel[] getComponents() {
		return startScreen.toArray(new EntityModel[0]);
	}

	public void appendToMessage(String string) {
		prompt.modifyMessage(message + string);
	}
}