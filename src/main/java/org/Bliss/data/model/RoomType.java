package org.Bliss.data.model;


import lombok.Getter;

@Getter
public enum RoomType {
   SINGLE("A Room with One Bed"),
    DOUBLE("A Room with Two Beds"),
    SUITE("A more Luxurious room with additional offers");

   private final String description;

   RoomType( String description) {
       this.description = description;
   }
    @Override
   public String toString() {
       return description;
   }
}
