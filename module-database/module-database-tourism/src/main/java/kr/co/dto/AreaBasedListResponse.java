package kr.co.dto;

import lombok.Data;

import java.util.List;

@Data
public class AreaBasedListResponse {
    private Response response;

    // Getters and Setters

    @Data
    public static class Response {
        private Header header;
        private Body body;

        // Getters and Setters
        @Data
        public static class Header {
            private String resultCode;
            private String resultMsg;

            // Getters and Setters
        }
        @Data
        public static class Body {
            private Items items;
            private int numOfRows;
            private int pageNo;
            private int totalCount;

            // Getters and Setters
            @Data
            public static class Items {
                private List<Item> item;

                // Getters and Setters
                @Data
                public static class Item {
                    private String addr1;
                    private String addr2;
                    private String areacode;
                    private String booktour;
                    private String cat1;
                    private String cat2;
                    private String cat3;
                    private String contentid;
                    private String contenttypeid;
                    private String createdtime;
                    private String firstimage;
                    private String firstimage2;
                    private String cpyrhtDivCd;
                    private String mapx;
                    private String mapy;
                    private String mlevel;
                    private String modifiedtime;
                    private String sigungucode;
                    private String tel;
                    private String title;
                    private String zipcode;

                    // Getters and Setters
                }
            }
        }
    }
}
