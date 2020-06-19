public class MemberDTO {
   
   
    private String amynum;
    private String name;
    private String birth;
    private String address;
    private String fdate;
    private String clas;
    private String spec; 
    private String posit;
    private String gunnum;
    
 
    //이클립스팁 : Getter/Setter 만들기
    //             우클릭 -> source->Generate Getters And Setters-> [Select AlL] -> [OK]
   
   
    public String getAmynum() {
        return amynum;
    }
    public void setAmynum(String amynum) {
        this.amynum = amynum;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getbirth() {
        return birth;
    }
    public void setbirth(String birth) {
        this.birth = birth;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFdate() {
        return fdate;
    }
    public void setFdate(String fdate) {
        this.fdate = fdate;
    }
    public String getClas() {
        return clas;
    }
    public void setClas(String clas) {
        this.clas = clas;
    }
    public String getSpec() {
        return spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public String getPosit() {
        return posit;
    }
    public void setPosit(String posit) {
        this.posit = posit;
    }
    public String getGunnum() {
        return gunnum;
    }
    public void setGunnum(String gunnum) {
        this.gunnum = gunnum;
    }
   
    //DTO 객체 확인
    //이클립스팁 : toString() 자동생성: 우클릭 -> source -> Generate toString->[OK]
    @Override
    public String toString() {
        return "MemberDTO [amynum=" + amynum + ", name=" + name + ", birth=" + birth
                + ", address=" + address + ", fdate=" + fdate + ", clas=" + clas
                + ", spec=" + spec + ", posit=" + posit + ", gunnum=" + gunnum
                + "]";
    }
}
