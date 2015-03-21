package javastudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenericsTest {
    static class Parent {}
    static class Child1 extends Parent {}
    static class Child2 extends Parent {}
    
    @Test
    public void test() {
        List<Child1> child1List = new ArrayList<>(Arrays.asList(new Child1()));
        List<? extends Parent> list = child1List;
        
        List<Child2> castedList = (List<Child2>)list; // これは例外にならない
        try {
            Child2 c2 = castedList.get(0); // ClassCastException
        } catch (ClassCastException e) {
            return;
        }
        fail();
    }

    @Test
    public void test2() {
        List<Child1> child1List = new ArrayList<>(Arrays.asList(new Child1()));
        List<? extends Parent> list = child1List;
        
        List<Child2> castedList = Collections.checkedList((List<Child2>)list, Child2.class); // これは例外にならない。listの内容はチェックされない？
        
    }
    
    @Test
    public void test3() {
        List<Child1> child1List = new ArrayList<>(Arrays.asList(new Child1()));
        List<? extends Parent> list = child1List;
        
        // listの内容がすべてChild2であることを前提にリストを作り直す。
        List<Child2> remakedList = new ArrayList<>();
        remakedList.add(list.size()-1, null);
        Collections.<Child2>copy(remakedList, (List<Child2>)list); // これは例外にならない。実行時には型の情報Child2は消去されているので。
    }
}
