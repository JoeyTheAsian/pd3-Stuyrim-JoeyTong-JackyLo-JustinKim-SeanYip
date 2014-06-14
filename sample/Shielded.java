import java.awt.Image;

public interface Shielded{
    public Image downShield = null;
    public Image upShield = null;
    public Image leftShield = null;
    public Image rightShield = null;
    public Image downShieldAnimated = null;
    public Image upShieldAnimated = null;
    public Image leftShieldAnimated = null;
    public Image rightShieldAnimated = null;
    public Image getUpShield();
    public Image getDownShield();
    public Image getLeftShield();
    public Image getRightShield();
    public Image getUpShieldAnimated();
    public Image getDownShieldAnimated();
    public Image getLeftShieldAnimated();
    public Image getRightShieldAnimated();
}
