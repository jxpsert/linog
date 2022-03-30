package linog.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class LinogLogo extends SpriteEntity {

	public LinogLogo(int x, int y, int width, int height) {
		super("backgrounds/linoglogo.png", new Coordinate2D(x - width / 2, y - height / 2), new Size(width, height));
	}
}
