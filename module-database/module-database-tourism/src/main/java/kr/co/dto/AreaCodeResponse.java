package kr.co.dto;

import lombok.Data;

import java.util.List;

@Data
public class AreaCodeResponse {
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;

        @Data
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Data
        public static class Body {
            private Items items;
            private int numOfRows;
            private int pageNo;
            private int totalCount;

            @Data
            public static class Items {
                private List<Item> item;

                @Data
                public static class Item {
                    private int rnum;
                    private String code;
                    private String name;
                }
            }
        }
    }
}
