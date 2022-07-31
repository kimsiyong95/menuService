package com.musinsa.menu.repository;

import com.musinsa.menu.domain.Menu;
import com.musinsa.menu.domain.QMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Menu> findMenuWithChildren(Long id) {

        QMenu parent = new QMenu("parent");
        QMenu child = new QMenu("child");

        return jpaQueryFactory.selectFrom(parent)
                .leftJoin(parent.parent)
                .fetchJoin()
                .where(
                        parent.parent.isNull()
                )
                .fetch();
    }
}
