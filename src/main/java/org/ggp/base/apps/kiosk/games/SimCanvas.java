package org.ggp.base.apps.kiosk.games;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.ggp.base.apps.kiosk.templates.GameCanvas_SimpleGrid;

public class SimCanvas extends GameCanvas_SimpleGrid {

	protected int selectedPoint = 0;
	protected int secondPoint = 0;

	@Override
	protected int getGridWidth() {
		return 75;
	}

	@Override
	protected int getGridHeight() {
		return 50;
	}

	@Override
	protected void renderCell(Graphics g, int x, int y) {
        int width = g.getClipBounds().width;
        int height = g.getClipBounds().height;

        //g.setColor(Color.BLACK);
        //g.drawRect(0, 0, width, height);

        if (isOnRedLine(x, y)) {
        	g.setColor(Color.RED);
        	g.fillRect(0,  0, width, height);
        }
        else if (isOnBlueLine(x, y)) {
        	g.setColor(Color.BLUE);
        	g.fillRect(0,  0, width, height);
        }
        if (isClickablePoint(x, y)) {
        	g.setColor(Color.BLACK);
        	g.fillRect(0,  0, width, height);
		    if (getSelectedPoint(x, y) == selectedPoint || getSelectedPoint(x, y) == secondPoint) {
		    	g.setColor(Color.GREEN);
		    	g.fillRect(0, 0, width, height);
		    }
        }

	}

	private boolean isOnBlueLine(int x, int y) {
		List<int[]> lines = getLines(x, y);
		for (int[] line : lines) {
			if (gameStateHasFact(String.format("( line %d %d b )", line[0], line[1] ))) {
				return true;
			}
		}
		return false;
	}

	private boolean isOnRedLine(int x, int y) {
		List<int[]> lines = getLines(x, y);
		for (int[] line : lines) {
			if (gameStateHasFact(String.format("( line %d %d r )", line[0], line[1] ))) {
				return true;
			}
		}
		return false;
	}

	private List<int[]> getLines(int x, int y) {
		List<int[]> lines = new ArrayList<>();
		if (y == 38 && x <= 31 && x >= 18) {
			lines.add(new int[] {1, 2});
		}
		if (y == 12 && x <= 31 && x >= 18) {
			lines.add(new int[] {4, 5});
		}
		if (y <= 38 && y >= 25 && y - x == 20) {
			lines.add(new int[] {1, 6});
		}
		if (y <= 25 && y >= 12 && x - y == 19) {
			lines.add(new int[] {3, 4});
		}
		if (y <= 38 && y >= 25 && x + y == 69) {
			lines.add(new int[] {2, 3});
		}
		if (y <= 25 && y >= 12 && x + y == 30) {
			lines.add(new int[] {5, 6});
		}
		if (y == 25 && x <= 44 && x >= 5) {
			lines.add(new int[] {3, 6});
		}
		if (x == 18 && y <= 38 && y >= 12) {
			lines.add(new int[] {1, 5});
		}
		if (x == 31 && y <= 38 && y >= 12) {
			lines.add(new int[] {2, 4});
		}
		if (y >= 12 && y <= 38 && 2*x-y == 24) {
			lines.add(new int[] {2, 5});
		}
		if (y >= 12 && y <= 38 && 2*x-y == 25) {
			lines.add(new int[] {2, 5});
		}
		if (y >= 12 && y <= 38 && 2*x+y == 74) {
			lines.add(new int[] {1, 4});
		}
		if (y >= 12 && y <= 38 && 2*x+y == 73) {
			lines.add(new int[] {1, 4});
		}
		if (x >= 18 && x <= 44 && 2*y-x == 6) {
			lines.add(new int[] {3, 5});
		}
		if (x >= 18 && x <= 44 && 2*y-x == 5) {
			lines.add(new int[] {3, 5});
		}
		if (x >= 5 && x <= 31 && 2*y-x == 45) {
			lines.add(new int[] {2, 6});
		}
		if (x >= 5 && x <= 31 && 2*y-x == 46) {
			lines.add(new int[] {2, 6});
		}
		if (x >= 18 && x <= 44 && 2*y+x == 94) {
			lines.add(new int[] {1, 3});
		}
		if (x >= 18 && x <= 44 && 2*y+x == 95) {
			lines.add(new int[] {1, 3});
		}
		if (x >= 5 && x <= 31 && 2*y+x == 55) {
			lines.add(new int[] {4, 6});
		}
		if (x >= 5 && x <= 31 && 2*y+x == 56) {
			lines.add(new int[] {4, 6});
		}

		return lines;
	}

	@Override
	protected void handleClickOnCell(int xCell, int yCell, int xWithin, int yWithin) {
		xCell -= 1;
		yCell -= 1;
		int clickedPoint = getSelectedPoint(xCell, yCell);
		if (selectedPoint == 0 && isClickablePoint(xCell, yCell)) {
			selectedPoint = clickedPoint;
			repaint();
		} else if (isClickablePoint(xCell, yCell)) {
			secondPoint = clickedPoint;
			if (secondPoint == selectedPoint) {
				secondPoint = 0;
				selectedPoint = 0;
				submitWorkingMove(null);
				return;
			}
			repaint();
			int source = Math.min(selectedPoint, clickedPoint);
			int target = Math.max(selectedPoint, clickedPoint);
			String move = String.format("( mark %d %d )", source, target);
			if (gameStateHasLegalMove(move)) {
				submitWorkingMove(stringToMove(move));
			}
		}
	}

	@Override
	public String getGameName() {
		return "Sim";
	}

	@Override
	protected String getGameKey() {
		return "sim";
	}

	@Override
	public void clearMoveSelection() {
		submitWorkingMove(null);
		selectedPoint = 0;
		secondPoint = 0;
        repaint();
	}

	protected boolean isClickablePoint(int x, int y) {
		return ((x == 5 && y == 25)
				|| (x == 44 && y == 25)
				|| (x == 18 && y == 12)
				|| (x == 31 && y == 12)
				|| (x == 18 && y == 38)
				|| (x == 31 && y == 38));
	}

	protected int getSelectedPoint(int x, int y) {
		 if (x == 5 && y == 25) {
			 return 6;
		 }
		 else if (x == 44 && y == 25) {
			 return 3;
		 }
		 else if (x == 18 && y == 12) {
			 return 5;
		 }
		 else if (x == 31 && y == 12) {
			 return 4;
		 }
		 else if (x == 18 && y == 38) {
			 return 1;
		 }
		 else if (x == 31 && y == 38) {
			 return 2;
		 }
		 return 0;
	}

}
