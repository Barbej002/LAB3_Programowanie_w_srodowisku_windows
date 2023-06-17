class RGBController {
    public void initColor(RGB color, int r_value, int g_value, int b_value) {
        color.setR_value(r_value);
        color.setG_value(g_value);
        color.setB_value(b_value);
    }

    public void displayColor(RGB color) {
        System.out.println("[" + color.getR_value() + ", " + color.getG_value() + ", " + color.getB_value() + "]");
    }

    public RGB mixColors(RGB color1, RGB color2) {
        int r = (color1.getR_value() + color2.getR_value()) / 2;
        int g = (color1.getG_value() + color2.getG_value()) / 2;
        int b = (color1.getB_value() + color2.getB_value()) / 2;
        return new RGB(r, g, b);
    }
}