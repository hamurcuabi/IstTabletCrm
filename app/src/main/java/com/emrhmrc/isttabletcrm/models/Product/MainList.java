package com.emrhmrc.isttabletcrm.models.Product;

public class MainList {
    private String inv_MainProductGroupName;

    private int ProductCount;

    private String inv_MainProductGroupid;

    private String Image;

    private String ImageMimeType;

    private int SubGroupCount;

    private double ImageFileSize;

    public MainList() {
    }

    @Override
    public String toString() {
        return "MainList{" +
                "inv_MainProductGroupName='" + inv_MainProductGroupName + '\'' +
                ", ProductCount=" + ProductCount +
                ", inv_MainProductGroupid='" + inv_MainProductGroupid + '\'' +
                ", Image='" + Image + '\'' +
                ", ImageMimeType='" + ImageMimeType + '\'' +
                ", SubGroupCount=" + SubGroupCount +
                ", ImageFileSize=" + ImageFileSize +
                '}';
    }

    public String getInv_MainProductGroupName() {
        return inv_MainProductGroupName;
    }

    public void setInv_MainProductGroupName(String inv_MainProductGroupName) {
        this.inv_MainProductGroupName = inv_MainProductGroupName;
    }

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int productCount) {
        ProductCount = productCount;
    }

    public String getInv_MainProductGroupid() {
        return inv_MainProductGroupid;
    }

    public void setInv_MainProductGroupid(String inv_MainProductGroupid) {
        this.inv_MainProductGroupid = inv_MainProductGroupid;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageMimeType() {
        return ImageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        ImageMimeType = imageMimeType;
    }

    public int getSubGroupCount() {
        return SubGroupCount;
    }

    public void setSubGroupCount(int subGroupCount) {
        SubGroupCount = subGroupCount;
    }

    public double getImageFileSize() {
        return ImageFileSize;
    }

    public void setImageFileSize(double imageFileSize) {
        ImageFileSize = imageFileSize;
    }
}
