import sys
f = open(sys.argv[1], 'r')
commands = [[line.split()] for line in f.readlines()]
f.close()
# x= dikey hareket // y = yatay hareket
def __init():
    pieces[7] = ['r1', 'n1', 'b1', 'qu', 'ki', 'b2', 'n2', 'r2']
    pieces[6] = ['p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'p7', 'p8']
    pieces[5] = ['  ', '  ', '  ', '  ', '  ', '  ', '  ', '  ']
    pieces[4] = ['  ', '  ', '  ', '  ', '  ', '  ', '  ', '  ']
    pieces[3] = ['  ', '  ', '  ', '  ', '  ', '  ', '  ', '  ']
    pieces[2] = ['  ', '  ', '  ', '  ', '  ', '  ', '  ', '  ']
    pieces[1] = ['P1', 'P2', 'P3', 'P4', 'P5', 'P6', 'P7', 'P8']
    pieces[0] = ['R1', 'N1', 'B1', 'QU', 'KI', 'B2', 'N2', 'R2']


def bishop_moves(bishop):
    position_i = 0
    position_j = 0
    is_black = True
    if bishop.startswith('b') or bishop.startswith('q'):
        is_black = False
    for i in range(8):
        for j in range(8):
            if pieces[i][j] == bishop:
                position_i = i
                position_j = j
    x = position_i
    y = position_j
    # sol üste gitme
    while x-1 >= 0 and y-1 >= 0:
        x -= 1
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    # sol alta gitme
    while x+1 <= 7 and y-1 >= 0:
        x += 1
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    # sağ üste gitme
    while x-1 >= 0 and y+1 <= 7:
        x -= 1
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    # sağ alta gitme
    while x+1 <= 7 and y+1 <= 7:
        x += 1
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break


def rook_moves(rook):
    position_i = 0
    position_j = 0
    is_black = True
    if rook.startswith('r') or rook.startswith('q'):
        is_black = False
    for i in range(8):
        for j in range(8):
            if pieces[i][j] == rook:
                position_i = i
                position_j = j
    x = position_i
    y = position_j
    #yukarı hareketi
    while x-1 >= 0:
        x -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    #aşağı hareketi
    while x+1 <= 7:
        x += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    #sağ hareketi
    while y+1 <= 7:
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break

    x = position_i
    y = position_j
    #sol hareketi
    while y-1 >= 0:
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        if possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
        if possible_pos in black_pieces or possible_pos in white_pieces:
            break


def queen_moves(queen):
    rook_moves(queen)
    bishop_moves(queen)


def knight_moves(knight):
    position_i = 0
    position_j = 0
    is_black = True
    if knight.startswith('n'):
        is_black = False
    for i in range(8):
        for j in range(8):
            if pieces[i][j] == knight:
                position_i = i
                position_j = j

    x = position_i
    y = position_j
    if x-2 >= 0 and y-1 >= 0:
        x -= 2
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x = position_i
    y = position_j
    if x + 2 <= 7 and y - 1 >= 0:
        x += 2
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x+2 <= 7 and y+1 <= 7:
        x += 2
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x-2 >= 0 and y+1 <= 7:
        x -= 2
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x-1 >= 0 and y-2 >= 0:
        x -= 1
        y -= 2
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x+1 <= 7 and y-2 >= 0:
        x += 1
        y -= 2
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x-1 >= 0 and y+2 <= 7:
        x -= 1
        y += 2
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    x =  position_i
    y = position_j
    if x+1 <= 7 and y+2 <= 7:
        x += 1
        y += 2
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])

    #solüst
    x =  position_i
    y = position_j
    if x-1 >= 0 and y-1 >= 0:
        x -= 1
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
    #solalt
    x =  position_i
    y = position_j
    if x+1 <= 7 and y-1 >= 0:
        x += 1
        y -= 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
    #sağüst
    x =  position_i
    y = position_j
    if x-1 >= 0 and y+1 <= 7 :
        x -= 1
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])
    #sağalt
    x =  position_i
    y = position_j
    if x+1 <= 7 and y+1 <= 7:
        x += 1
        y += 1
        possible_pos = pieces[x][y]
        if possible_pos not in white_pieces and not is_black:
            legal_moves.append(board[x][y])
        elif possible_pos not in black_pieces and is_black:
            legal_moves.append(board[x][y])


def pawn_moves(pawn):
    position_i = 0
    position_j = 0
    is_black = True
    if pawn.startswith('p'):
        is_black = False
    for i in range(8):
        for j in range(8):
            if pieces[i][j] == pawn:
                position_i = i
                position_j = j

    x = position_i
    y = position_j
    if is_black and x+1 <= 7:
        x += 1
        if y-1 >= 0:
            if pieces[x][y - 1] in white_pieces:
                legal_moves.append(board[x][y-1])
        if y+1 <=7:
            if pieces[x][y + 1] in white_pieces:
                legal_moves.append(board[x][y+1])
        if pieces[x][y] == '  ':
            legal_moves.append(board[x][y])
    elif not is_black and x-1 >= 0:
        x -= 1
        if y-1 >= 0:
            if pieces[x][y - 1] in white_pieces:
                legal_moves.append(board[x][y-1])
        if y+1 <=7:
            if pieces[x][y + 1] in white_pieces:
                legal_moves.append(board[x][y+1])
        if pieces[x][y] == '  ':
            legal_moves.append(board[x][y])


def king_moves(king):
    position_i = 0
    position_j = 0
    is_black = True
    if king.startswith('k'):
        is_black = False
    for i in range(8):
        for j in range(8):
            if pieces[i][j] == king:
                position_i = i
                position_j = j

    x = position_i
    y = position_j
    #siyah
    if is_black:
        #yukarı
        if x - 1 >= 0:
            if pieces[x-1][y] not in black_pieces:
                legal_moves.append(board[x-1][y])
        #aşşa
        if x + 1 <= 7:
            if pieces[x+1][y] not in black_pieces:
                legal_moves.append(board[x+1][y])
        #saa
        if y + 1 <= 7:
            if pieces[x][y + 1] not in black_pieces:
                legal_moves.append(board[x][y + 1])

        #sola
        if y - 1 >= 0:
            if pieces[x][y - 1] not in black_pieces:
                legal_moves.append(board[x][y - 1])

        #sağyukaru
        if y + 1 <= 7 and x-1 >= 0:
            if pieces[x-1][y+1] not in black_pieces:
                legal_moves.append(board[x-1][y+1])

        #solyukaru
        if y - 1 >= 0 and x - 1 >= 0:
            if pieces[x - 1][y - 1] not in black_pieces:
                legal_moves.append(board[x - 1][y - 1])

        #sağaşşa
        if y + 1 <= 7 and x + 1 <= 7:
            if pieces[x + 1][y + 1] not in black_pieces:
                legal_moves.append(board[x + 1][y + 1])

        #solaşşa
        if y - 1 >= 0 and x + 1 <= 7:
            if pieces[x + 1][y - 1] not in black_pieces:
                legal_moves.append(board[x + 1][y - 1])



    else:
        #beyaz
        # yukarı
        if x - 1 >= 0:
            if pieces[x - 1][y] not in white_pieces:
                legal_moves.append(board[x - 1][y])
        # aşşa
        if x + 1 <= 7:
            if pieces[x + 1][y] not in white_pieces:
                legal_moves.append(board[x + 1][y])
        # saa
        if y + 1 <= 7:
            if pieces[x][y + 1] not in white_pieces:
                legal_moves.append(board[x][y + 1])

        # sola
        if y - 1 >= 0:
            if pieces[x][y - 1] not in white_pieces:
                legal_moves.append(board[x][y - 1])

        # sağyukaru
        if y + 1 <= 7 and x - 1 >= 0:
            if pieces[x - 1][y + 1] not in white_pieces:
                legal_moves.append(board[x - 1][y + 1])

        # solyukaru
        if y - 1 >= 0 and x - 1 >= 0:
            if pieces[x - 1][y - 1] not in white_pieces:
                legal_moves.append(board[x - 1][y - 1])

        # sağaşşa
        if y + 1 <= 7 and x + 1 <= 7:
            if pieces[x + 1][y + 1] not in white_pieces:
                legal_moves.append(board[x + 1][y + 1])

        # solaşşa
        if y - 1 >= 0 and x + 1 <= 7:
            if pieces[x + 1][y - 1] not in white_pieces:
                legal_moves.append(board[x + 1][y - 1])


def move(piece, destination):
    pos_x = 0
    pos_y = 0
    if destination in legal_moves:
        for i in range(8):
            for j in range(8):
                if destination == board[i][j]:
                    pos_x = i
                    pos_y = j

        for i in range(8):
            for j in range(8):
                if piece == pieces[i][j]:
                    pieces[i][j] = '  '

        pieces[pos_x][pos_y] = piece
        print('OK')

    else:
        print('Failure')


def __print():
    for i in pieces:
        for j in i:
            print(j, end=' ')
        print()


def __sort():
    for j in range(8):
        for i in range(7,-1,-1):
            if board[i][j] in legal_moves:
                sorted_legal_moves.append(board[i][j])

turn = True
pieces = [['  '] * 8 for i in range(8)]
letter = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
number = ['8', '7', '6', '5', '4', '3', '2', '1']
squares = []
board = []
white_pieces = ['ki', 'qu', 'r1', 'r2', 'b1', 'b2', 'n1', 'n2', 'p1', 'p2', 'p3', 'p4', 'p5', 'p6', 'p7', 'p8']
black_pieces = ['KI', 'QU', 'R1', 'R2', 'B1', 'B2', 'N1', 'N2', 'P1', 'P2', 'P3', 'P4', 'P5', 'P6', 'P7', 'P8']
for i in number:
    for j in letter:
        squares.append(j+i)
j = 0
for i in range(1, len(squares)):
    if i % 8 == 0:
        board.append(squares[j:i])
        j = i
board.append(squares[j:])

legal_moves = []
sorted_legal_moves = []
__init()
__print()
for j in commands:
    for i in j:
        print('\n')
        legal_moves = []
        sorted_legal_moves = []
        if i[0].startswith('init'):
            __init()
            __print()
        elif i[0].startswith('show'):
            p = i[1]
            if p.lower().startswith('b'):
                bishop_moves(p)
            elif p.lower().startswith('r'):
                rook_moves(p)
            elif p.lower().startswith('q'):
                queen_moves(p)
            elif p.lower().startswith('n'):
                knight_moves(p)
            elif p.lower().startswith('k'):
                king_moves(p)
            else:
                pawn_moves(p)

            __sort()
            print(sorted_legal_moves)

        elif i[0].startswith('move'):
            p = i[1]
            d = i[2]
            if p.lower().startswith('b'):
                bishop_moves(p)
            elif p.lower().startswith('r'):
                rook_moves(p)
            elif p.lower().startswith('q'):
                queen_moves(p)
            elif p.lower().startswith('n'):
                knight_moves(p)
            elif p.lower().startswith('k'):
                knight_moves(p)
            else:
                pawn_moves(p)

            move(p, d)

        elif i[0].startswith('pri'):
            __print()

        elif i[0].startswith('exit'):
            exit()






