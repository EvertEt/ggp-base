package org.ggp.base.apps.kiosk.games;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.ggp.base.apps.kiosk.GameCanvas;

public class SimCanvas extends GameCanvas {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final static int CIRCLE_DIAMETER = 20;
	private List<Point> points = new ArrayList<>();
	private Point firstPoint;
	private Point secondPoint;
	private int firstPointIndex = -1;
	private int secondPointIndex = -1;

	public SimCanvas() {
		super();
		points.add(new Point(120, 100));
		points.add(new Point(220, 100));
		points.add(new Point(20, 200));
		points.add(new Point(320, 200));
		points.add(new Point(120, 300));
		points.add(new Point(220, 300));
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
		firstPoint = null;
		firstPointIndex = -1;
		secondPoint = null;
		secondPointIndex = -1;
	}

	@Override
	protected void paintGame(Graphics g) {
		int width = g.getClipBounds().width;
        int height = g.getClipBounds().height;
        g.setColor(Color.BLACK);
        for (Point point : points) {
			if (point.equals(firstPoint) || point.equals(secondPoint)) {
        		g.setColor(Color.GREEN);
        	} else {
        		g.setColor(Color.BLACK);
        	}
        	g.fillOval(point.x, point.y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        }
        paintLinesOfColor(g, "r", Color.RED);
        paintLinesOfColor(g, "b", Color.BLUE);
	}

	private void paintLinesOfColor(Graphics g, String role, Color color) {
		g.setColor(color);
		for (int i = 0; i <= 5; i++) {
        	for (int j = i+1; j <= 6; j++) {
        		Graphics2D g2 = (Graphics2D) g;
        		g2.setStroke(new BasicStroke(3));
        		if (gameStateHasFact(String.format("( line %d %d %s )", i+1, j+1, role))) {
        			Point p1 = points.get(i);
        			Point p2 = points.get(j);
        			g.drawLine(p1.x + CIRCLE_DIAMETER / 2, p1.y + CIRCLE_DIAMETER / 2, p2.x + CIRCLE_DIAMETER / 2, p2.y + CIRCLE_DIAMETER / 2);
        		}
        	}
        }
	}

	@Override
	protected void handleClickEvent(int x, int y) {
		x-=10;
		y-=20;
		Point clickedPoint = null;
		int index = -1;
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			double distance = Math.sqrt(Math.pow((p.x - x), 2) + Math.pow((p.y - y), 2));
			System.out.println(distance);
			if (distance < CIRCLE_DIAMETER) {
				System.out.println("Clicked a circle");
				clickedPoint = p;
				index = i + 1;
			}
		}
		if (clickedPoint != null && clickedPoint.equals(firstPoint)) {
			firstPoint = null;
			firstPointIndex = -1;
			secondPoint = null;
			secondPointIndex = -1;
			repaint();
		}
		else if (clickedPoint != null && firstPoint == null) {
			firstPoint = clickedPoint;
			firstPointIndex = index;
			repaint();
		} else if (clickedPoint != null) {
			secondPoint = clickedPoint;
			secondPointIndex = index;
			repaint();
		}
		if (firstPoint != null && secondPoint != null) {
			String move = String.format("( mark %d %d )", Math.min(firstPointIndex, secondPointIndex), Math.max(firstPointIndex, secondPointIndex));
			if (gameStateHasLegalMove(move)) {
				submitWorkingMove(stringToMove(move));
			}
		}
	}
}