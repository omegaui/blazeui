/*
 * The TextField Component of Blaze UI

 * Super Flexible UI Element -- In the Making
 * Copyright (C) 2022 Omega UI

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package blazeui.component;
import omegaui.listener.KeyStrokeListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import blazeui.component.plugin.PaintBoard;

import javax.swing.JComponent;

/*
 * CaretField isn't completed yet!
 * 
 * But it can be utilized to input text that just fits in the field area.
 */

public class CaretField extends JComponent{

	public PaintBoard borderPaintBoard;
	public PaintBoard backgroundPaintBoard;
	public PaintBoard caretPaintBoard;
	public PaintBoard textPaintBoard;

	public String text;
	public String toolTipText;

	public Color color1; // Border Color
	public Color color2; // Background Color
	public Color color3; // Text Color
	public Color color4; // Caret Color
	public Color color5; // Label Text Color
	public Color color6; // Active Border Color

	public int textX = 6;
	public int textY;
	public int textWidth;
	public int textHeight;

	public int arcX = 6;
	public int arcY = 6;

	public int caretX;
	public int caretY;
	public int caretWidth = 2;
	public int caretHeight;

	public int caretXPos = 0;
	public int caretPosition = -1;

	public KeyStrokeListener keyStrokeListener;

	public volatile boolean enter = false;

	public Runnable action;

	public CaretField(String text, String toolTipText, Color color1, Color color2, Color color3, Color color4, Color color5, Color color6){
		this.text = text;
		this.toolTipText = toolTipText;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.color4 = color4;
		this.color5 = color5;
		this.color6 = color6;
		init();
	}

	public void init(){
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				enter = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e){
				enter = false;
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e){
				if(isFocusable())
					grabFocus();
				repaint();
			}
		});

		addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e){
				repaint();
			}
			@Override
			public void focusLost(FocusEvent e){
				repaint();
			}
		});

		keyStrokeListener = new KeyStrokeListener(this);

		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				int code = e.getKeyCode();
				if(code == KeyEvent.VK_LEFT)
					moveCaretToLeft();
				else if(code == KeyEvent.VK_RIGHT)
					moveCaretToRight();
				else if(code == KeyEvent.VK_HOME)
					moveCaretToStart();
				else if(code == KeyEvent.VK_END)
					moveCaretToEnd();
				else if(code == KeyEvent.VK_ENTER)
					action.run();
				else if(code == KeyEvent.VK_BACK_SPACE && !text.isEmpty() && caretPosition > -1){
					//Removing Keys
					if(text.length() > 1){
						if(caretPosition > 0){
							text = text.substring(0, caretPosition) + text.substring(caretPosition + 1);
						}
						else if(caretPosition == 0){
							text = text.substring(1);
						}
					}
					else
						text = "";
					caretPosition--;
				}
				else if(isKeyPaintable(code)){
					//Inserting Keys
					if(caretPosition < 0)
						text = e.getKeyChar() + text;
					else if(text.length() > 1)
						text = text.substring(0, caretPosition + 1) + e.getKeyChar() + text.substring(caretPosition + 1);
					else
						text += e.getKeyChar();
					caretPosition++;
				}
				repaint();
			}
		});

		borderPaintBoard = (g)->{
			if(!enter)
				g.setColor(isFocusOwner() ? color6 : color1);
			else
				g.setColor(color5);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), arcX, arcY);
		};

		backgroundPaintBoard = (g)->{
			g.setColor(color2);
			g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arcX, arcY);
		};

		caretPaintBoard = (g)->{
			if(!isFocusOwner())
				return;
			g.setColor(color4);
			caretX = computeCaretX();
			caretY = computeCaretY();
			g.fillRect(caretX, caretY, caretWidth, caretHeight = computeCaretHeight());
		};

		textPaintBoard = (g)->{
			textWidth = g.getFontMetrics().stringWidth(text);
			textHeight = g.getFontMetrics().getHeight();
			textY = getHeight()/2 - textHeight/2 + g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent() + 1;
			if(text.isEmpty() && !isFocusOwner()){
				g.setColor(color5);
				g.drawString(toolTipText, textX, textY);
			}
			else{
				g.setColor(color3);
				g.drawString(text, textX, textY);
				for(int i = 0; i < text.length(); i++){
					System.out.print(text.charAt(i) + "");
				}
				System.out.println();
				for(int i = 0; i < caretPosition; i++){
					System.out.print(" ");
				}
				System.out.print("^");
				System.out.println();
			}
		};
	}

	public boolean moveCaretToLeft(){
		if(text.isEmpty() || caretPosition <= -1)
			return false;

		Graphics2D g = (Graphics2D)getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int lastCharWidth = g.getFontMetrics().charWidth(text.charAt(caretPosition));
		int computedX = computeCaretX(caretXPos - lastCharWidth);
		
		if(computedX > textX || computedX >= textX - g.getFontMetrics().charWidth(text.charAt(0))){
			caretXPos -= lastCharWidth;
			caretPosition--;
			return true;
		}
		return false;
	}

	public boolean moveCaretToRight(){
		if(text.isEmpty() || caretPosition >= text.length() - 1)
			return false;
		Graphics2D g = (Graphics2D)getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int lastCharWidth = g.getFontMetrics().charWidth(text.charAt(caretPosition == -1 ? 0 : caretPosition));
		int computedWidth = computeCaretX(caretXPos + lastCharWidth);
		
		if(computedWidth <= textWidth + textX){
			caretXPos += lastCharWidth;
			caretPosition++;
			return true;
		}
		return false;
	}

	public void moveCaretToStart(){
		if(text.isEmpty())
			return;
		while(moveCaretToLeft());
	}

	public void moveCaretToEnd(){
		if(text.isEmpty())
			return;
		while(moveCaretToRight());
	}

	public int computeCaretX(){
		if(text.isEmpty())
			return textX;
		return textX + textWidth + caretXPos;
	}

	public int computeCaretX(int caretXPos){
		if(text.isEmpty())
			return textX;
		return textX + textWidth + caretXPos;
	}

	public int computeCaretY(){
		return getHeight()/2 - textHeight/2;
	}

	public int computeCaretHeight(){
		return textHeight;
	}

	public boolean isKeyPaintable(int code){
		return switch(code){
			case KeyEvent.VK_CONTROL:
			case KeyEvent.VK_ALT:
			case KeyEvent.VK_SHIFT:
			case KeyEvent.VK_ESCAPE:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_HOME:
			case KeyEvent.VK_END:
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_BACK_SPACE:
			case KeyEvent.VK_DELETE:
			case KeyEvent.VK_CAPS_LOCK:
			case KeyEvent.VK_TAB:
			case KeyEvent.VK_PAUSE:
			case KeyEvent.VK_PRINTSCREEN:
			case KeyEvent.VK_ENTER:
				yield false;
			default:
				yield !(code >= KeyEvent.VK_F1 && code <= KeyEvent.VK_F2);
		};
	}

	@Override
	public void paintComponent(Graphics graphics){
		Graphics2D g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		borderPaintBoard.paint(g);
		backgroundPaintBoard.paint(g);
		textPaintBoard.paint(g);
		caretPaintBoard.paint(g);

		super.paintComponent(g);
	}

	public java.lang.String getText() {
		return text;
	}

	public void setText(java.lang.String text) {
		this.text = text;
		repaint();
	}

	public void setOnAction(Runnable r){
		this.action = r;
		if(action == null)
			action = ()->{};
	}
}

