package com.end.demo.api;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.end.demo.vo.HolidayVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class XmlParsing {
    public static ArrayList<HolidayVO> getData(String year_str) {
        int month = 1;
        ArrayList<HolidayVO> holiArray = new ArrayList<>();

        while (true) {

            try {


                String month_str = String.format("%02d", month);
                String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"
                        + "?ServiceKey=xmC%2BjGC54CVDu10NeLGYRiDspIcpRsV%2FJaZgjUdCHr21WCur4UPh0TWrXvq9u92o3BlzfWuxVROiV1lHq%2BcHEg%3D%3D"
                        + "&solYear=" + year_str + "&solMonth=" + month_str + "&numOfRows=20";

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                //Parse the content of the given URI as an XML documentand return a new DOM Document object.
                Document doc = dBuilder.parse(url);

                // DOM Tree가 XML 문서의 구조대로 완성될 수 있게 한다.
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                System.out.println("파싱할 리스트 수 : " + nList.getLength());


                for (int temp = 0; temp < nList.getLength(); temp++) {
                    HolidayVO dto = new HolidayVO();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        String rawDate = getTagValue("locdate", eElement);
                        String locDate = rawDate.substring(0, 4) + "-" + rawDate.substring(4, 6) + "-" + rawDate.substring(6, 8);

                        // 현재TimeStamp
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:s");
                        Calendar cal = Calendar.getInstance();
                        String today = null;
                        today = formatter.format(cal.getTime());
                        Timestamp ts = Timestamp.valueOf(today);

                        dto.setYear("2020");
                        dto.setHoli_flag(getTagValue("isHoliday", eElement));
                        dto.setDate_kind(getTagValue("dateKind", eElement));
                        dto.setLocdate_name(getTagValue("dateName", eElement));
                        dto.setLocdate(locDate);
                        dto.setReg_date(ts.toString());

                        //IP
                        InetAddress local;
                        try {
                            local = InetAddress.getLocalHost();
                            String ip = local.getHostAddress();
                            dto.setReg_ip(ip);

                            // 로그

                            System.out.println("=======================");
                            System.out.println("년도: 2020");
                            System.out.println("휴무 유무: " + getTagValue("isHoliday", eElement));
                            System.out.println("휴일 종류: " + getTagValue("dateKind", eElement));
                            System.out.println("명칭: " + getTagValue("dateName", eElement));
                            System.out.println("일자: " + locDate);
                            System.out.println("등록일: " + ts.toString());
                            System.out.println("등록IP: " + ip);
                        } catch (UnknownError e1) {
                            e1.printStackTrace();
                        }


                        holiArray.add(dto);

                    }    // for end
                }    // if end


            } catch (Exception e) {
                e.printStackTrace();
            }    // try~catch end
            month++;
            if (month > 13) {
                break;
            }
        }
        return holiArray;

    } //getData method end

    // tag값의 정보를 가져오는 메소드
    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }
}
