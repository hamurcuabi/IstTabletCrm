package com.emrhmrc.isttabletcrm.models.Product;

public class SubList {
    private String inv_SubProductGroupName;
    private int ProductCount;
    private String inv_SubProductGroupid;
    private String Image;
    private String ImageMimeType;
    private double ImageFileSize;

    public SubList() {
    }

    public String getInv_SubProductGroupName() {
        return inv_SubProductGroupName;
    }

    @Override
    public String toString() {
        return "SubList{" +
                "inv_SubProductGroupName='" + inv_SubProductGroupName + '\'' +
                ", ProductCount=" + ProductCount +
                ", inv_SubProductGroupid='" + inv_SubProductGroupid + '\'' +
                ", Image='" + Image + '\'' +
                ", ImageMimeType='" + ImageMimeType + '\'' +
                ", ImageFileSize=" + ImageFileSize +
                '}';
    }

    public void setInv_SubProductGroupName(String inv_SubProductGroupName) {
        this.inv_SubProductGroupName = inv_SubProductGroupName;
    }

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int productCount) {
        ProductCount = productCount;
    }

    public String getInv_SubProductGroupid() {
        return inv_SubProductGroupid;
    }

    public void setInv_SubProductGroupid(String inv_SubProductGroupid) {
        this.inv_SubProductGroupid = inv_SubProductGroupid;
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

    public double getImageFileSize() {
        return ImageFileSize;
    }

    public void setImageFileSize(double imageFileSize) {
        ImageFileSize = imageFileSize;
    }
}
