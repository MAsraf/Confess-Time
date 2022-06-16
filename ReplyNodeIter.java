package confesstime;

import java.util.Iterator;

public class ReplyNodeIter<T> implements Iterator<ReplyNode<T>> {

    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private ReplyNode<T> treeNode;

    public ReplyNodeIter(ReplyNode<T> replyNode) {
        this.treeNode = replyNode;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = replyNode.children.iterator();
    }

    private ProcessStages doNext;
    private ReplyNode<T> next;
    private Iterator<ReplyNode<T>> childrenCurNodeIter;
    private Iterator<ReplyNode<T>> childrenSubNodeIter;

    @Override
    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.treeNode;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                ReplyNode<T> childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            } else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            } else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }

    @Override
    public ReplyNode<T> next() {
        return this.next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
