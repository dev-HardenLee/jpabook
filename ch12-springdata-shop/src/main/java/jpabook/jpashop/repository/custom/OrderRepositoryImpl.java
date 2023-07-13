package jpabook.jpashop.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.QMember;
import jpabook.jpashop.domain.QOrder;

/**
 * @author holyeye
 */
public class OrderRepositoryImpl extends QueryDslRepositorySupport implements CustomOrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {

        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return null;
    }
}

