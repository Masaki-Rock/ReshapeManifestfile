package com.tools.format;

import java.util.HashSet;
import java.util.Set;

/**
 * PackageXML情報DTOクラス
 * Date --- Author ---- Subject
 * 20160503 M.Kawaguchi プログラムリリース
 **/
public class CMN_MetaDto {

    public CMN_MetaDto() {
    }

    private String name;
 
    private Set<String> members;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void addMember(String mem) {
   
        if (this.members == null) {
            this.members = new HashSet<String>();
        }
        this.members.add(mem);
    }
}
