package com.sily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sily.entity.ForumBoard;
import com.sily.entity.constants.Constants;
import com.sily.mapper.ForumBoardMapper;
import com.sily.service.IForumBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章板块信息 服务实现类
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@Service
public class ForumBoardServiceImpl extends ServiceImpl<ForumBoardMapper, ForumBoard> implements IForumBoardService {


    @Resource
    private IForumBoardService iForumBoardService;


    @Override
    public List<ForumBoard> getBoardTree(Integer postType) {
        LambdaQueryWrapper<ForumBoard> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(ForumBoard::getSort);
        List<ForumBoard> list = iForumBoardService.list(queryWrapper);
        return convertList2Tree(list, Constants.P_BOARD_ID);
    }


    private List<ForumBoard> convertList2Tree(List<ForumBoard> boardList, Integer pid){
        List<ForumBoard> children = new ArrayList<>();
        for (ForumBoard fb : boardList){
            if (fb.getpBoardId().equals(pid)){
                fb.setChildren(convertList2Tree(boardList,fb.getBoardId()));
                children.add(fb);
            }
        }
        return children;
    }
}
