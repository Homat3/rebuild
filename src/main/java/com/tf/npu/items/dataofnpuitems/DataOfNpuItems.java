package com.tf.npu.items.dataofnpuitems;

public class DataOfNpuItems
{
  public boolean isSpawnEgg;
  public String ID;
  public String creature_ID;

  public String backgroundColor;
  public String highlightColor;

  public Integer getBackgroundColor()
  {
      if (isSpawnEgg)
          return Color.valueOf(backgroundColor).getValue();
      else
          return null;
  }

  public Integer getHighlightColor()
  {
      if (isSpawnEgg)
          return Color.valueOf(highlightColor).getValue();
      else
          return null;
  }

  private enum Color
  {
      BLUE(0x0000FF), ORANGE(0xFFA500), YELLOW(0xFFFF00), GREEN(0x00FF00), PURPLE(0xA020F0), GREY(0xBEBEBE);

      final Integer color;

      Color(Integer color)
      {
          this.color = color;
      }

      Integer getValue()
      {
          return color;
      }
  }
}
