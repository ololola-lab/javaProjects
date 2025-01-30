//CountInversions(Lst):
//        if len(lst) <= 1:
//            return 0
//        inversions = 0
//
//        LeftHalf = List
//        RightHalf = правая половина List
//        inversions = inversions + CountInversions(LeftHalf)
//        inversions = inversions + CountInversions(RightHalf)
//        sort(RightHalf) // необходимо для двоичного поиска
//        for x in LeftHalf:
//        inversions = inversions + CountSmaller(RightHalf,x)
//        return inversions