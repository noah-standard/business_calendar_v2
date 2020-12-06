package com.end.demo.lib;

public class Pager {



    // 페이지당 게시물 수
    private int PAGE_SCALE = 5; //public static final 에서 private 수정
    //화면당 페이지 수
    private int BLOCK_SCALE = 5; //public static final 에서 private 수정
    private int curPage; // 현재페이지
    private int prevPage; // 이전페이지
    private int nextPage; // 다음페이지
    private int totPage; // 전체페이지 갯수
    private int totBlock; // 전체페이지 블록갯수
    private int curBlock; // 현재페이지 블록갯수
    private int prevBlock; // 이전페이지 블록갯수
    private int nextBlock; // 다음페이지 블록갯수
    // where rn between #{start} and #{end}
    private int pageBegin;
    private int pageEnd;
    //[이전] blockBegin 42 43 44 ... blockEnd [다음]
    private int blockBegin; //현재페이지 블록의 시작번호
    private int blockEnd; // 현재페이지 블록의 끝번호

    // 생성자
    // Pager(레코드 갯수, 현재 페이지 번호)
    public Pager(int count, int curPage,int list_height, int list_page){
        curBlock = 1;// 현재 페이지 블록 번호
        this.curPage = curPage; // 현재페이지 설정
        setPAGE_SCALE(list_height); // 각게시판 리스트 갯수
        setBLOCK_SCALE(list_page); // 각게시판 페이징 블록갯수
        setTotPage(count); // 현재 페이지 갯수 계산
        // between #{start} and #{end} 에 입력될 값 계산
        setPageRange();
        setTotBlock();// 전체 페이지 블록 갯수 계산
        //페이지 블록의 시작,끝 번호 계산
        setBlockRange();
    }

    public void setPAGE_SCALE(int PAGE_SCALE) {
        this.PAGE_SCALE = PAGE_SCALE;
    }

    public void setBLOCK_SCALE(int BLOCK_SCALE) {
        this.BLOCK_SCALE = BLOCK_SCALE;
    }

    public void setBlockRange(){
        // 현재 페이지가 몇번째 페이지 블록에 속하는지 계산
        curBlock = (int)Math.ceil((curPage-1)/BLOCK_SCALE+1);
        // 현재 페이지 블록의 시작, 끝 번호 계산
        blockBegin = (curBlock-1)*BLOCK_SCALE + 1;
        blockEnd = blockBegin + BLOCK_SCALE -1;
        // 마지막 페이지 번호가 범위를 초과하지 않도록 처리
        if(blockEnd > totPage) blockEnd = totPage;
        // [이전]을 눌렀을 때 이동할 페이지 번호
        prevPage = (curBlock == 1) ? 1 : (curBlock - 1)* BLOCK_SCALE;
        // [다음]을 눌렀을 때 이동할 페이지 번호
        nextPage = (curBlock>totBlock) ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
        // 마지막 페이지가 범위를 초과하지 않도록 처리
        if(nextPage >= totPage) nextPage=totPage;

    }
    public int getPageScale() {
        return PAGE_SCALE;
    }

    public int getBlockScale() {
        return BLOCK_SCALE;
    }

    public void setPageRange(){
        // where rn between #{start} and #{end} 에 입력된 값
        // 시작번호 = (현재페이지 -1 ) * 페이지 당 게시물수 + 1 ;
        pageBegin = (curPage - 1 )* PAGE_SCALE +1;
        // 끝번호 = 시자번호+ 페이지당 게시물수 -1;
        pageEnd = pageBegin + PAGE_SCALE -1;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotPage() {
        return totPage;
    }

    public void setTotPage(int count) {
        // Math.ceil(실수) 올림 처리
        this.totPage = (int)Math.ceil(count*1.0 / PAGE_SCALE);
    }

    public int getTotBlock() {
        return totBlock;
    }

    // 페이지 블록의 갯수 계산(총 100페이지라면 10개 블록)
    public void setTotBlock() {
        this.totBlock = (int)Math.ceil(totPage / BLOCK_SCALE);
    }

    public int getCurBlock() {
        return curBlock;
    }

    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }

    public int getPrevBlock() {
        return prevBlock;
    }

    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getPageBegin() {
        return pageBegin;
    }

    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getBlockBegin() {
        return blockBegin;
    }

    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }

    public int getBlockEnd() {
        return blockEnd;
    }

    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
}
