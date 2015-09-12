import numpy as np
import operator as op


def solve():
    limit = 1000
    multiples = [3, 5]
    r = np.arange(min(multiples), limit)

    return sum(r[reduce(op.or_, [np.mod(r, m) == 0 for m in multiples])])
