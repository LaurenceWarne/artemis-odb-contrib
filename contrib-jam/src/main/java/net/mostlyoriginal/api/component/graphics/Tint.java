package net.mostlyoriginal.api.component.graphics;

import com.artemis.annotations.Fluid;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import net.mostlyoriginal.api.component.common.ExtendedComponent;
import net.mostlyoriginal.api.component.common.Tweenable;

/**
 * Colorize for animations, labels.
 * <p/>
 * Optional, convention is to assume white if not set.
 *
 * @see Color
 * @author Daan van Yperen
 */
@Fluid(swallowGettersWithParameters=true)
public class Tint extends ExtendedComponent<Tint> implements Tweenable<Tint> {

	public static final Tint WHITE = new Tint(Color.WHITE);
	public static final Tint TRANSPARENT = new Tint("ffffff00");

	public Color color = new Color();

	public Tint() {
	}

	public Tint(Color color) {
		set(color);
	}

	public Tint(Tint tint) {
		set(tint);
	}

	public Tint(float r, float g, float b, float a) {
		set(r, g, b, a);
	}

	@Override
	protected void reset() {
		color.set(0, 0, 0, 0);
	}

	/**
	 * Create Tint by hex, RRGGBBAA.
	 */
	public Tint(String hex) {
		setHex(hex);
	}

	/**
	 * Set color to hex, RRGGBBAA.
	 */
	public void setHex(String hex) {
		set((float) Integer.valueOf(hex.substring(0, 2), 16) / 255.0F,
				(float) Integer.valueOf(hex.substring(2, 4), 16) / 255.0F,
				(float) Integer.valueOf(hex.substring(4, 6), 16) / 255.0F,
				(float) (hex.length() != 8 ? 255 : Integer.valueOf(hex.substring(6, 8), 16)) / 255.0F);
	}

	public void set(Color color) {
		this.color.set(color);
	}

	public void set(float r, float g, float b, float a) {
		this.color.r = r;
		this.color.g = g;
		this.color.b = b;
		this.color.a = a;
	}

	@Override
	public void set(Tint tint) {
		this.color.r = tint.color.r;
		this.color.g = tint.color.g;
		this.color.b = tint.color.b;
		this.color.a = tint.color.a;
	}

	@Override
	public void tween(Tint a, Tint b, float value) {

		final Interpolation linear = Interpolation.linear;

		final Color colorA = a.color;
		final Color colorB = b.color;

		color.r = linear.apply(colorA.r, colorB.r, value);
		color.g = linear.apply(colorA.g, colorB.g, value);
		color.b = linear.apply(colorA.b, colorB.b, value);
		color.a = linear.apply(colorA.a, colorB.a, value);
	}
}
