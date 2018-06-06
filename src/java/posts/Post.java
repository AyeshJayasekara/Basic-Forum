/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts;

/**
 *
 * @author shanelm1
 */
public class Post {
    private int id;
    private String created;
    private String modified;
    private String title;
    private String descri;
    private int postedby;
    private String expl;
    private String URL1;
    private String URL2;
    private String URL3;
    private int cate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getPostedby() {
        return postedby;
    }

    public void setPostedby(int postedby) {
        this.postedby = postedby;
    }

    public String getExpl() {
        return expl;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

    public String getURL1() {
        return URL1;
    }

    public void setURL1(String URL1) {
        this.URL1 = URL1;
    }

    public String getURL2() {
        return URL2;
    }

    public void setURL2(String URL2) {
        this.URL2 = URL2;
    }

    public String getURL3() {
        return URL3;
    }

    public void setURL3(String URL3) {
        this.URL3 = URL3;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }
    
    
}
