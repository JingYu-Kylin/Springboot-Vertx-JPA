package com.xiongba.ams.message;

import com.xiongba.ams.model.FileInfo;
import com.xiongba.ams.protocol.Protocol;
import lombok.Data;

import java.util.List;

@Data
public class AuthCheckVersionResponse extends BaseMessage {
    private short versionGame;
    private short versionData;
    private List<FileInfo> filelist;
    private String fileListUrl;
    private String announcement;
    private short isStrong;

    public AuthCheckVersionResponse() {
        super(Protocol.MSG_CATEGORY_AUTH.getNum(), Protocol.MSG_ID_AUTH_CHECK_VERSION_RESPONSE.getNum());
        versionGame = 0;
        versionData = 0;
        filelist = null;
        fileListUrl = "";
        announcement = "";
        isStrong = 0;
    }

//    public boolean encode(Buffer os) {
//        super.encode(os);
//        os.write(versionGame);
//        os.write(versionData);
//
//        if (filelist != null && filelist.size() != 0) {
//            short fileListSize = (short) filelist.size();
//            os.write(fileListSize);
//            if (fileListSize > 0) {
//                for (int it = 0; it < fileListSize; it++) {
//                    FileInfo em = (FileInfo) filelist.get(it);
//                    em.encode(os);
//                }
//            }
//        } else {
//            short fileListSize = 0;
//
//            os.write(fileListSize);
//            if (fileListSize > 0) {
//                for (int it = 0; it < fileListSize; it++) {
//                    FileInfo em = (FileInfo) filelist.get(it);
//                    em.encode(os);
//                }
//            }
//        }
//
////		os.write(filelist);
//        os.write(fileListUrl);
//        os.write(announcement);
//        os.write(isStrong);
//        return true;
//    }
//
//    public boolean decode(MessageInputStream is) throws Exception {
//        super.decode(is);
//        versionGame = is.read(versionGame);
//        versionData = is.read(versionData);
//        short filelistSize = 0;
//        filelistSize = is.read(filelistSize);
//        if (filelistSize > 0) {
//            filelist = new ArrayList<FileInfo>();
//            for (int it = 0; it < filelistSize; it++) {
//                FileInfo fileInfo = new FileInfo();
//                fileInfo.decode(is);
//                filelist.add(fileInfo);
//            }
//        }
////		filelist = is.read(filelist);
//        fileListUrl = is.read(fileListUrl);
//        announcement = is.read(announcement);
//        isStrong = is.read(isStrong);
//        return true;
//    }
}