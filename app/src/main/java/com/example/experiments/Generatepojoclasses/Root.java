package com.example.experiments.Generatepojoclasses;

import java.util.List;

public class Root {

    private boolean error;

    private List<AvailableCategory> AvailableCategory;

    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setAvailableCategory(List<AvailableCategory> AvailableCategory){
        this.AvailableCategory = AvailableCategory;
    }
    public List<AvailableCategory> getAvailableCategory(){
        return this.AvailableCategory;
    }

    public class AvailableCategory
    {
        private String type;

        private String type_id;

        private String image;

        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setType_id(String type_id){
            this.type_id = type_id;
        }
        public String getType_id(){
            return this.type_id;
        }
        public void setImage(String image){
            this.image = image;
        }
        public String getImage(){
            return this.image;
        }
    }
}
