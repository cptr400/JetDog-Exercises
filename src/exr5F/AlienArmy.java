package exr5F;

import java.util.ArrayList;

import common.Configuration.Direction;

/**
 * @author Krish Pillai
 */
public class AlienArmy {

	private final AlienSoldier[][] aliens;

	private int offset = 100;
	private int xgap = 60, ygap = 40;
	private int size = 0;

	public AlienArmy(int rank, int file) {
		aliens = new AlienSoldier[rank][file];
		init();
	}

	private void init() {
		for (int rank = 0; rank < aliens.length; rank++) {
			for (int file = 0; file < aliens[rank].length; file++) {
				int xoff = offset + file * xgap;
				int yoff = offset + rank * ygap;
				aliens[rank][file] = new AlienSoldier(xoff, yoff);
				aliens[rank][file].setRank(rank);
				aliens[rank][file].setFile(file);
				size++;
			}
		}
		findFrontLine();
	}

	public void findFrontLine() {
		for (int file = 0; file < aliens[0].length; file++) {
			for (int rank = aliens.length - 1; rank >= 0; rank--) {
				if (aliens[rank][file] == null)
					continue;
				
				aliens[rank][file].setFrontLine(true);
				break;
			}
		}
	}


	public void delete(int rank, int file) {
		AlienSoldier a = aliens[rank][file];
		aliens[rank][file] = null;
		if (a.isFrontLine()) {
			findFrontLine();
		}
		size--;
	}

	public int getRank(AlienSoldier alien) {
		return alien.getRank();
	}

	public int getFile(AlienSoldier alien) {
		return alien.getFile();
	}

	public Alien[] getAliens() {
		ArrayList<AlienSoldier> list = new ArrayList<>();
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[i].length; j++) {
				list.add(aliens[i][j]);
			}
		}
		return (AlienSoldier[]) list.toArray(new AlienSoldier[0]);
	}

	public void delete(AlienSoldier a) {
		delete(a.getRank(), a.getFile());
	}

	public void setActive(boolean active) {
		for (int rank = 0; rank < aliens.length; rank++) {
			for (int file = 0; file < aliens[rank].length; file++) {

				AlienSoldier a = aliens[rank][file];
				if (a == null)
					continue;
				a.setActive(active);
			}
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void setVelocity(float velocity) {
		for (int rank = 0; rank < aliens.length; rank++) {
			for (int file = 0; file < aliens[rank].length; file++) {

				AlienSoldier a = aliens[rank][file];
				if (a == null)
					continue;
				a.setXVelocity(velocity);;
			}
		}
	}

	
	public void advance(Direction edge) {
		for (int rank = 0; rank < aliens.length; rank++) {
			for (int file = 0; file < aliens[rank].length; file++) {
				
				if (aliens[rank][file] == null)
					continue;
				
				AlienSoldier a = aliens[rank][file];

				if (edge == Direction.East) {
					a.moveLeft();
				} else {
					a.moveRight();
				}
				a.stepForward();
				a.speedUp();
			}
		}
		
	}
}
