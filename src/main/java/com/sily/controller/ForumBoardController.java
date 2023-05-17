package com.sily.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.common.R;
import com.sily.entity.ForumBoard;
import com.sily.entity.constants.Constants;
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
    public R listAll() {
        return R.success(iForumBoardService.getBoardTree(Constants.P_BOARD_ID));
    }

    /**
     * 保存模块
     *
     * @param forumBoard
     * @return
     */
    @PostMapping("/board/saveBoard")
    public R save(@RequestBody ForumBoard forumBoard) {
        return iForumBoardService.save(forumBoard) ? R.success("保存成功") : R.success("保存失败");
    }

    /**
     * 删除模块
     *
     * @param forumBoard
     * @return
     */
    @DeleteMapping("/board/delBoard")
    public R del(@RequestBody ForumBoard forumBoard) {
        log.info("删除模块,被删除的id为" + forumBoard.getBoardId());
        log.info(forumBoard.toString());
        return iForumBoardService.removeById(forumBoard) ? R.success("删除成功") : R.success("删除失败");
    }

    /**
     * 修改模块
     *
     * @param forumBoard
     * @return
     */
    @PutMapping("/board/changeBoard")
    public R change(@RequestBody ForumBoard forumBoard) {
        log.info("修改模块,被修改的id为" + forumBoard.getBoardId());
        log.info(forumBoard.toString());
        return iForumBoardService.updateById(forumBoard) ? R.success("修改成功") : R.success("修改失败");
    }

}
