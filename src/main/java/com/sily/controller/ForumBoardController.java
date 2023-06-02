package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.annoation.IP;
import com.sily.annoation.VerifyParam;
import com.sily.common.R;
import com.sily.entity.ForumBoard;
import com.sily.entity.constants.Constants;
import com.sily.entity.enums.VerifyRegexEnum;
import com.sily.service.IForumBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章板块信息 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@RestController
@Slf4j
public class ForumBoardController {

    @Autowired
    private IForumBoardService iForumBoardService;

    /**
     * 获取所有板块
     *
     * @return
     */
    @GetMapping("/board/loadBoard")
    @IP(getIp = true)
    public R listAll() {
        return R.success(iForumBoardService.getBoardTree(Constants.P_BOARD_ID));
    }


    /**
     * 保存板块
     * @param boardId
     * @param pBoardId
     * @param boardName
     * @param boardDesc
     * @param cover
     * @return
     */
    @PostMapping("/board/saveBoard")
    public R save(Integer boardId,
                  @VerifyParam(required = true) Integer pBoardId,
                  @VerifyParam(required = true, regex = VerifyRegexEnum.NUMBER_LETTER_UNDER_LINE) String boardName,
                  @VerifyParam(required = true, regex = VerifyRegexEnum.NUMBER_LETTER_UNDER_LINE) String boardDesc,
                  String cover) {
        ForumBoard forumBoard = new ForumBoard();
        if (boardId!=null){
            forumBoard.setBoardId(boardId);
        }
        if (cover!=null){
            forumBoard.setCover(cover);
        }
        forumBoard.setpBoardId(pBoardId);
        forumBoard.setBoardName(boardName);
        forumBoard.setBoardDesc(boardDesc);
        return iForumBoardService.save(forumBoard) ? R.success("保存成功") : R.success("保存失败");
    }

    /**
     * 删除板块
     * @param boardId
     * @return
     */
    @DeleteMapping("/board/delBoard")
    public R delete(@VerifyParam(required = true) String boardId) {
        LambdaQueryWrapper<ForumBoard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumBoard::getBoardId,boardId);
        return iForumBoardService.remove(queryWrapper) ? R.success("删除成功") : R.success("删除失败");
    }

    /**
     * 修改板块
     * @param boardId
     * @return
     */
    @PostMapping("/board/changeBoardSort")
    public R changeBoardSort(@VerifyParam(required = true) String boardId) {
        LambdaQueryWrapper<ForumBoard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ForumBoard::getBoardId,boardId);
        return iForumBoardService.update(queryWrapper) ? R.success("修改成功") : R.success("修改失败");
    }

}
