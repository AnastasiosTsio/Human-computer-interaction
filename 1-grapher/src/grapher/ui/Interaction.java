package grapher.ui;

import java.awt.*;
import java.awt.event.*;

public class Interaction implements MouseListener, MouseMotionListener, MouseWheelListener {
    private static final int DRAG_DETECTION_LIMIT = 3;
    private static final int ZOOM_FACTOR = 5; // (As a percentage)
    private static final BasicStroke RECTANGLE_STROKE = new BasicStroke(2);

    private Point beginningPoint;
    private Point endingPoint;
    private State state = State.DEFAULT;
    private final Grapher grapher;

    public Interaction(Grapher grapher) {
        this.grapher = grapher;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endingPoint = e.getPoint();
        onDragStep();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        onWheelStep(e.getPoint(), e.getWheelRotation());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        beginningPoint = e.getPoint();
        onMousePressedStep(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endingPoint = e.getPoint();
        onReleaseStep();
    }

    public void drawFeedback(Graphics2D g) {
        if (state == State.RIGHT_DRAGGED) {
            drawCoolRect(g);
        }
    }

    private void drawCoolRect(Graphics2D g) {
        Stroke lastStroke = g.getStroke();
        g.setStroke(RECTANGLE_STROKE);

        int a = Math.min(beginningPoint.x, endingPoint.x);
        int b = Math.min(beginningPoint.y, endingPoint.y);
        int c = Math.abs(beginningPoint.x - endingPoint.x);
        int d = Math.abs(beginningPoint.y - endingPoint.y);
        g.drawRect(a, b, c, d);
        g.setStroke(lastStroke);
    }

    private void onReleaseStep() {
        if (state == State.RIGHT_DRAGGED && !beginningPoint.equals(endingPoint)) {
            grapher.zoom(beginningPoint, endingPoint);
        } else if (state == State.LEFT_PRESSED) {
            grapher.zoom(endingPoint, ZOOM_FACTOR);
        } else if (state == State.RIGHT_PRESSED) {
            grapher.zoom(endingPoint, -ZOOM_FACTOR);
        }
        state = State.DEFAULT;
        grapher.setCursor(Cursor.getDefaultCursor());
    }

    private void onMousePressedStep(MouseEvent e) {
        if (state == State.DEFAULT) {
            switch (e.getButton()) {
                case MouseEvent.BUTTON1 : state = State.LEFT_PRESSED; break;
                case MouseEvent.BUTTON3 : state = State.RIGHT_PRESSED; break;
                default : state = State.DEFAULT; break;
            }
            grapher.repaint();
        }
    }

    private void translate(int x, int y) {
        int dx = x - beginningPoint.x;
        int dy = y - beginningPoint.y;
        grapher.translate(dx, dy);
        beginningPoint = new Point(x, y);
    }

    private void onWheelStep(Point point, int notches) {
        if (state == State.DEFAULT) {
            grapher.zoom(point, -notches * ZOOM_FACTOR);
        }
    }

    private void onDragStep() {
        double distance = endingPoint.distance(beginningPoint);
        if (distance > DRAG_DETECTION_LIMIT) {
            switch (state) {
                case LEFT_PRESSED :state = State.LEFT_DRAGGED; break;
                case RIGHT_PRESSED : state = State.RIGHT_DRAGGED; break;
                default : break;
            }
            ;
            grapher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        if (state == State.LEFT_DRAGGED) {
            translate((int)endingPoint.getX(), (int)endingPoint.getY());
        } else if (state == State.RIGHT_DRAGGED) {
            grapher.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // UNUSED
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // UNUSED
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // UNUSED
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // UNUSED
    }
}

enum State {
    DEFAULT,
    LEFT_PRESSED,
    RIGHT_PRESSED,
    LEFT_DRAGGED,
    RIGHT_DRAGGED,
}
